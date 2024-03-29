package co.com.cleanarchitecture.api.controllers;


import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cleanarchitecture.api.dto.BrandDTO;
import co.com.cleanarchitecture.api.exceptions.MissingDataException;
import co.com.cleanarchitecture.api.exceptions.ResourceNotFoundException;
import co.com.cleanarchitecture.api.util.Utility;
import co.com.cleanarchitecture.model.brand.Brand;
import co.com.cleanarchitecture.usecase.brand.BrandUseCase;
import lombok.AllArgsConstructor;
import technicalogs.gateways.LoggerRepository;

@RestController
@RequestMapping(value = "/api/v1/brands", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BrandController {

    private BrandUseCase beanBrandUseCase;
    private LoggerRepository logger;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody BrandDTO data,
                                  BindingResult bindingResult) {
        logger.info("Saving ----->" + data.toBuilder().toString());

        if (bindingResult.hasErrors()) {
            return Utility.validateRequest(bindingResult);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(beanBrandUseCase.save(data.convertToEntity(data)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody BrandDTO brandDTO,
                                    BindingResult bindingResult,
                                    @PathVariable Long id) {
        logger.info("Updating ----->" + brandDTO.toBuilder().toString());

        validateIfExistBrandById(id);

        if (bindingResult.hasErrors()) {
            return Utility.validateRequest(bindingResult);
        }

        brandDTO.setId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(beanBrandUseCase.update(
                        brandDTO.convertToEntity(brandDTO)));
    }

    @GetMapping
    public ResponseEntity<List<Brand>> getAll() {
        List<Brand> brandList = beanBrandUseCase.getAll();
        return new ResponseEntity<>(brandList, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Brand> show(@PathVariable("id") Long id) {
        validateIfExistBrandById(id);
        return new ResponseEntity<>(beanBrandUseCase.getById(id), HttpStatus.OK);
    }

    @GetMapping("/page/{page}/{size}")
    public ResponseEntity<Page<Brand>> getAllPageable(@PathVariable Integer page, @PathVariable Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Brand> brandList = beanBrandUseCase.getAll();
        Page<Brand> pages = new PageImpl<>(brandList, pageable, brandList.size());
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable("id") Long id) {
        validateIfExistBrandById(id);
        beanBrandUseCase.deleteById(id);
    }

    private void validateIfExistBrandById(Long id) {
        if (Objects.isNull(id)) {
            throw new MissingDataException();
        }
        Brand brandBD = beanBrandUseCase.getById(id);
        if (Objects.isNull(brandBD)) {
            throw new ResourceNotFoundException();
        }
    }

}

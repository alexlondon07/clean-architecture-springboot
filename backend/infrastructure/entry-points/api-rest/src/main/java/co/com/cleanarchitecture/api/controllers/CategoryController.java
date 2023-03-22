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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cleanarchitecture.api.dto.CategoryDTO;
import co.com.cleanarchitecture.api.exceptions.MissingDataException;
import co.com.cleanarchitecture.api.exceptions.ResourceNotFoundException;
import co.com.cleanarchitecture.api.util.Utility;
import co.com.cleanarchitecture.model.category.Category;
import co.com.cleanarchitecture.usecase.category.CategoryUseCase;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import technicalogs.gateways.LoggerRepository;

@RestController
@RequestMapping(value = "/api/v1/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class CategoryController {

    private final CategoryUseCase beanCategoryUseCase;
    private final LoggerRepository logger;

    @PostMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody CategoryDTO categoryDto,
                                  BindingResult bindingResult) {
        logger.info("Saving category ----->" + categoryDto.toBuilder().toString());

        if (bindingResult.hasErrors()) {
            return Utility.validateRequest(bindingResult);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(beanCategoryUseCase.saveCategory(categoryDto.convertToEntity(categoryDto)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody CategoryDTO categoryDto,
                                    BindingResult bindingResult,
                                    @PathVariable Long id) {
        logger.info("Updating category ----->" + categoryDto.toBuilder().toString());

        validateIfExistCategoryById(id);

        if (bindingResult.hasErrors()) {
            return Utility.validateRequest(bindingResult);
        }

        categoryDto.setId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(beanCategoryUseCase.updateCategory(
                        categoryDto.convertToEntity(categoryDto)));
    }

    @ApiOperation(value = "Get Category list ", response = Iterable.class, tags = "getAll")
    @GetMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categoryList = beanCategoryUseCase.getCategories();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Category> show(@PathVariable("id") Long id) {
        validateIfExistCategoryById(id);
        return new ResponseEntity<>(beanCategoryUseCase.getById(id), HttpStatus.OK);
    }

    @GetMapping("/page/{page}/{size}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Page<Category>> getAllPageable(@PathVariable Integer page, @PathVariable Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Category> categoryList = beanCategoryUseCase.getCategories();
        Page<Category> pages = new PageImpl<>(categoryList, pageable, categoryList.size());
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        validateIfExistCategoryById(id);
        beanCategoryUseCase.deleteById(id);
    }

    @GetMapping({"/{id}/{enable}"})
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public void enableCategory(@PathVariable("id") Long id,
                               @PathVariable("enable") Boolean enable) {
        validateIfExistCategoryById(id);
        beanCategoryUseCase.enable(id, enable);
    }

    private void validateIfExistCategoryById(Long id) {
        if (Objects.isNull(id)) {
            throw new MissingDataException();
        }
        Category categoryBB = beanCategoryUseCase.getById(id);
        if (Objects.isNull(categoryBB)) {
            throw new ResourceNotFoundException();
        }
    }
}

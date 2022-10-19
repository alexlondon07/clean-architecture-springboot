package co.com.cleanarchitecture.api;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cleanarchitecture.api.exceptions.MissingDataException;
import co.com.cleanarchitecture.api.exceptions.ResourceNotFoundException;
import co.com.cleanarchitecture.model.category.Category;
import co.com.cleanarchitecture.model.typeidentification.TypeDocIdentification;
import co.com.cleanarchitecture.usecase.typedocidentification.TypeDocIdentificationUseCase;
import lombok.AllArgsConstructor;
import technicalogs.gateways.LoggerRepository;

@RestController
@RequestMapping(value = "/v1/api/type-identifications", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TypeDocIdentificationApiRest {

    private final TypeDocIdentificationUseCase typeDocIdentificationUseCase;
    private final LoggerRepository logger;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<TypeDocIdentification> typeDocIdentificationList = typeDocIdentificationUseCase
                .getTypeDocIdentifications();
        return new ResponseEntity<>(typeDocIdentificationList, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        validateIfExistTypeDocIdentificationById(id);
        return new ResponseEntity<>(
                typeDocIdentificationUseCase.getById(id), HttpStatus.OK);
    }

    private void validateIfExistTypeDocIdentificationById(Long id) {
        if (Objects.isNull(id)) {
            throw new MissingDataException();
        }
        TypeDocIdentification typeDocIdentificationDB = typeDocIdentificationUseCase.getById(id);
        if (Objects.isNull(typeDocIdentificationDB)) {
            throw new ResourceNotFoundException();
        }
    }

}

package co.com.cleanarchitecture.api.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cleanarchitecture.api.exceptions.MissingDataException;
import co.com.cleanarchitecture.api.exceptions.ResourceNotFoundException;
import co.com.cleanarchitecture.model.typeidentification.TypeDocIdentification;
import co.com.cleanarchitecture.usecase.typedocidentification.TypeDocIdentificationUseCase;
import lombok.AllArgsConstructor;
import technicalogs.gateways.LoggerRepository;

@RestController
@RequestMapping(value = "/api/v1/type-identifications", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TypeDocIdentificationController {

    private final TypeDocIdentificationUseCase typeDocIdentificationUseCase;
    private final LoggerRepository logger;

    @GetMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<TypeDocIdentification>> getAll() {
        List<TypeDocIdentification> typeDocIdentificationList = typeDocIdentificationUseCase
                .getTypeDocIdentifications();
        return new ResponseEntity<>(typeDocIdentificationList, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<TypeDocIdentification> show(@PathVariable("id") Long id) {
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

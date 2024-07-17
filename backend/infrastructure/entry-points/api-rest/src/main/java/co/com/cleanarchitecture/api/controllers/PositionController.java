package co.com.cleanarchitecture.api.controllers;

import co.com.cleanarchitecture.api.exceptions.MissingDataException;
import co.com.cleanarchitecture.api.exceptions.ResourceNotFoundException;
import co.com.cleanarchitecture.api.util.Constants;
import co.com.cleanarchitecture.model.position.Position;
import co.com.cleanarchitecture.usecase.position.PositionUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = Constants.API_VERSION_V1 + "positions", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = Constants.ORIGIN_FRONTEND)
@AllArgsConstructor
@Validated
public class PositionController {

    private final PositionUseCase positionUseCase;

    @GetMapping("/{id}")
    @PreAuthorize(Constants.ROLE_MODERADOR_AND_ADMIN)
    public ResponseEntity<Position> show(@PathVariable Long id) {
        return new ResponseEntity<>(validateIfPositionExistsById(id), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize(Constants.ROLE_MODERADOR_AND_ADMIN)
    public ResponseEntity<List<Position>> getAll() {
        List<Position> positionList = positionUseCase.getAll();
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }

    private Position validateIfPositionExistsById(Long id) {
        if (Objects.isNull(id)) {
            throw new MissingDataException();
        }
        Position position = positionUseCase.getById(id);
        if (Objects.isNull(position)) {
            throw new ResourceNotFoundException();
        }
        return position;
    }
}

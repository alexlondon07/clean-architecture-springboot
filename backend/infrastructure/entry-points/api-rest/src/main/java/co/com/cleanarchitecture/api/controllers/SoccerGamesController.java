package co.com.cleanarchitecture.api.controllers;

import co.com.cleanarchitecture.api.dto.MessageResponse;
import co.com.cleanarchitecture.api.dto.SoccerGamesDTO;
import co.com.cleanarchitecture.api.exceptions.MissingDataException;
import co.com.cleanarchitecture.api.exceptions.ResourceNotFoundException;
import co.com.cleanarchitecture.api.util.Constants;
import co.com.cleanarchitecture.api.util.Utility;
import co.com.cleanarchitecture.model.soccergames.SoccerGames;
import co.com.cleanarchitecture.usecase.soccergames.SoccerGamesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = Constants.API_VERSION_V1 + "soccer-games", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = Constants.ORIGIN_FRONTEND)
@AllArgsConstructor
@Validated
public class SoccerGamesController {
    private final SoccerGamesUseCase beanSoccerGamesUseCase;

    @GetMapping
    @PreAuthorize(Constants.ROLE_MODERADOR_AND_ADMIN)
    public ResponseEntity<List<SoccerGames>> getAll() {
        List<SoccerGames> soccerGamesList = beanSoccerGamesUseCase.getAllSoccerGames();
        return new ResponseEntity<>(soccerGamesList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize(Constants.ROLE_MODERADOR_AND_ADMIN)
    public ResponseEntity<SoccerGames> show(@PathVariable Long id) {
        return new ResponseEntity<>(validateIfExistSoccerGamesById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(Constants.ROLE_MODERADOR_AND_ADMIN)
    public ResponseEntity<?> save(@Valid @RequestBody SoccerGamesDTO soccerGamesDTO,
                                  BindingResult bindingResult) {

        Utility.getResponseEntity(bindingResult);

        if(soccerGamesDTO.getPlayers().isEmpty()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Players list is required"));
        }

        SoccerGames soccerGames = beanSoccerGamesUseCase.save(soccerGamesDTO.convertToEntity(soccerGamesDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(soccerGames);
    }

    private SoccerGames validateIfExistSoccerGamesById(Long id) {
        if (Objects.isNull(id)) {
            throw new MissingDataException();
        }
        SoccerGames soccerGames = beanSoccerGamesUseCase.getById(id);
        if (Objects.isNull(soccerGames)) {
            throw new ResourceNotFoundException();
        }
        return soccerGames;
    }
}

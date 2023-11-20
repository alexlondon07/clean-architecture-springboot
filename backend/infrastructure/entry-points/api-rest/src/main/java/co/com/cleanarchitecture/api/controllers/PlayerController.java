package co.com.cleanarchitecture.api.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cleanarchitecture.api.util.Constants;
import co.com.cleanarchitecture.model.player.Player;
import co.com.cleanarchitecture.usecase.player.PlayerUseCase;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = Constants.API_VERSION_V1 + "players", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = Constants.ORIGIN_FRONTEND)
@AllArgsConstructor
public class PlayerController {

    private final PlayerUseCase beanPlayerUseCase;

    @GetMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Player>> getAll() {
        List<Player> players = beanPlayerUseCase.getAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

}

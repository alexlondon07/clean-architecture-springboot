package co.com.cleanarchitecture.api.controllers;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cleanarchitecture.api.dto.CategoryDTO;
import co.com.cleanarchitecture.api.dto.PlayerDTO;
import co.com.cleanarchitecture.api.exceptions.MissingDataException;
import co.com.cleanarchitecture.api.exceptions.ResourceNotFoundException;
import co.com.cleanarchitecture.api.util.Constants;
import co.com.cleanarchitecture.api.util.Utility;
import co.com.cleanarchitecture.model.player.Player;
import co.com.cleanarchitecture.usecase.player.PlayerUseCase;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = Constants.API_VERSION_V1 + "players", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = Constants.ORIGIN_FRONTEND)
@AllArgsConstructor
@Validated
public class PlayerController {

    private final PlayerUseCase beanPlayerUseCase;

    @GetMapping
    @PreAuthorize(Constants.ROLE_MODERADOR_AND_ADMIN)
    public ResponseEntity<List<Player>> getAll() {
        List<Player> players = beanPlayerUseCase.getAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize(Constants.ROLE_MODERADOR_AND_ADMIN)
    public ResponseEntity<Player> showPlayer(@PathVariable Long id) {
        validateIfExistPlayerById(id);
        return new ResponseEntity<>(beanPlayerUseCase.getById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(Constants.ROLE_MODERADOR_AND_ADMIN)
    public ResponseEntity<?> save(@Valid @RequestBody PlayerDTO playerDTO,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Utility.validateRequest(bindingResult);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(beanPlayerUseCase.save(playerDTO.convertToEntity(playerDTO)));
    }


    @PutMapping("/{id}")
    @PreAuthorize(Constants.ROLE_MODERADOR_AND_ADMIN)
    public ResponseEntity<?> update(@Valid @RequestBody PlayerDTO playerDTO,
                                    BindingResult bindingResult,
                                    @PathVariable Long id) {

        validateIfExistPlayerById(id);

        if (bindingResult.hasErrors()) {
            return Utility.validateRequest(bindingResult);
        }

        playerDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(beanPlayerUseCase.update(playerDTO.convertToEntity(playerDTO)));
    }

    private void validateIfExistPlayerById(Long id) {
        if (Objects.isNull(id)) {
            throw new MissingDataException();
        }
        if (Objects.isNull(beanPlayerUseCase.getById(id))) {
            throw new ResourceNotFoundException();
        }
    }

}

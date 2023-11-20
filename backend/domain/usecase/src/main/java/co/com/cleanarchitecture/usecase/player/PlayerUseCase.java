package co.com.cleanarchitecture.usecase.player;

import java.util.Collections;
import java.util.List;

import co.com.cleanarchitecture.model.player.Player;
import co.com.cleanarchitecture.model.player.gateways.PlayerRepository;
import lombok.RequiredArgsConstructor;
import technicalogs.gateways.LoggerRepository;

@RequiredArgsConstructor
public class PlayerUseCase {

    private final PlayerRepository repository;
    private final LoggerRepository logger;

    public List<Player> getAllPlayers(){
        try {
            logger.info("Successfully retrieved players list");
            return repository.getAll();
        }catch (Exception e){
            logger.error("Error Getting players list -->", e);
            return Collections.emptyList();

        }
    }
}

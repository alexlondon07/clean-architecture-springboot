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
            return repository.getAll();
        }catch (Exception e){
            logger.error("Error Getting players list -->", e);
            return Collections.emptyList();
        }
    }

    public Player getById(Long id){
        return repository.findById(id);
    }

    public Player findByCellphone(String cellphone){
        return repository.findByCellphone(cellphone);
    }

    public Player save(Player player) {
        try {
            return repository.save(player);
        } catch (Exception ex) {
            logger.error("Error saving player  -->", ex);
            return null;
        }
    }

    public Player update(Player player) {
        try {
            logger.info("Updating player with data : " + player.toString());
            return repository.save(player);
        } catch (Exception ex) {
            logger.error("Error updating player  -->", ex);
            return null;
        }
    }
}

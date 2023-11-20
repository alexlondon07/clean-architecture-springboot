package co.com.cleanarchitecture.jpa.player;

import java.util.List;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import co.com.cleanarchitecture.jpa.helper.AdapterOperations;
import co.com.cleanarchitecture.model.player.Player;
import co.com.cleanarchitecture.model.player.gateways.PlayerRepository;

@Repository
public class PlayerDataAdapter extends AdapterOperations<
        Player, PlayerData, Long, PlayerDataRepository>
implements PlayerRepository {

    public PlayerDataAdapter(PlayerDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, playerData -> mapper.map(playerData, Player.class));
    }

    @Override
    public List<Player> getAll() {
        return super.findAll();
    }

    @Override
    public Player findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}

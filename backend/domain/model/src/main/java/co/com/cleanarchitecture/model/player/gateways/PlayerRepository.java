package co.com.cleanarchitecture.model.player.gateways;

import java.util.List;

import co.com.cleanarchitecture.model.player.Player;

public interface PlayerRepository  {

    Player save(Player player);

    List<Player> getAll();

    void delete(Long id);

    Player findById(Long id);

}

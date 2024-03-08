package co.com.cleanarchitecture.model.soccergames;

import java.util.List;

public interface SoccerGamesRepository {

    SoccerGames save(SoccerGames soccerGames);

    List<SoccerGames> getAll();

    SoccerGames findById(Long id);
}


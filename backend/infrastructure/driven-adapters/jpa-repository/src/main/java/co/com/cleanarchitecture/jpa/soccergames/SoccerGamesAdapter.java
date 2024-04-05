package co.com.cleanarchitecture.jpa.soccergames;


import co.com.cleanarchitecture.jpa.helper.AdapterOperations;
import co.com.cleanarchitecture.model.soccergames.SoccerGames;
import co.com.cleanarchitecture.model.soccergames.SoccerGamesRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SoccerGamesAdapter extends AdapterOperations<SoccerGames, SoccerGamesData, Long, SoccerGamesDataRepository>
        implements SoccerGamesRepository {

    public SoccerGamesAdapter(SoccerGamesDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, soccerGamesData -> mapper.map(soccerGamesData, SoccerGames.class));
    }

    @Override
    public List<SoccerGames> getAll() {
        return super.findAllByOrderByField("DESC", "id");
    }
}

package co.com.cleanarchitecture.usecase.soccergames;

import co.com.cleanarchitecture.model.soccergames.SoccerGames;
import co.com.cleanarchitecture.model.soccergames.SoccerGamesRepository;
import lombok.RequiredArgsConstructor;
import technicalogs.gateways.LoggerRepository;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class SoccerGamesUseCase {
    private final SoccerGamesRepository soccerGamesRepository;
    private final LoggerRepository logger;

    public SoccerGames save(SoccerGames data) {
        try {
            logger.info("Saving SoccerGames " + data.toString());
            return soccerGamesRepository.save(data);
        } catch (Exception ex) {
            logger.error("Error saving SoccerGames with data " + data.toString(), ex);
            return null;
        }
    }

    public List<SoccerGames> getAllSoccerGames() {
        try {
            return soccerGamesRepository.getAll();
        } catch (Exception ex) {
            logger.error("Error Getting SoccerGames list -->", ex);
            return Collections.emptyList();
        }
    }

    public SoccerGames getById(Long id) {
        return soccerGamesRepository.findById(id);
    }
}

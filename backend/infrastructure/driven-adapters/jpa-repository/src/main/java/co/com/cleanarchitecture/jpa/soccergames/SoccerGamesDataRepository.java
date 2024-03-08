package co.com.cleanarchitecture.jpa.soccergames;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface SoccerGamesDataRepository extends PagingAndSortingRepository<SoccerGamesData, Long>,
        QueryByExampleExecutor<SoccerGamesData> {
}

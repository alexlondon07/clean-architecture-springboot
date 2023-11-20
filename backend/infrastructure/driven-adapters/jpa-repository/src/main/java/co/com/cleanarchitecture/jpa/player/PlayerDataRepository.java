package co.com.cleanarchitecture.jpa.player;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PlayerDataRepository extends PagingAndSortingRepository<PlayerData, Long>,
        QueryByExampleExecutor<PlayerData> {

}

package co.com.cleanarchitecture.jpa.position;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PositionDataRepository extends CrudRepository<PositionData, Long>,
        QueryByExampleExecutor<PositionData> {
}

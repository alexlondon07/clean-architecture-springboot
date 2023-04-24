package co.com.cleanarchitecture.jpa.role;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface RoleDataRepository extends PagingAndSortingRepository<RoleData, Long>,
        QueryByExampleExecutor<RoleData> {

    RoleData findByName(String name);

}



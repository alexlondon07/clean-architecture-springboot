package co.com.cleanarchitecture.jpa.rol;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface RoleDataRepository extends PagingAndSortingRepository<RoleData, Long>,
        QueryByExampleExecutor<RoleData> {


}



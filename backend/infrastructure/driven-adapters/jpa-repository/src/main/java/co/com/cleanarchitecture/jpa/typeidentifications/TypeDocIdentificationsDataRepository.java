package co.com.cleanarchitecture.jpa.typeidentifications;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface TypeDocIdentificationsDataRepository extends PagingAndSortingRepository
        <TypeDocIdentificationsData, Long>,
        QueryByExampleExecutor<TypeDocIdentificationsData> {
}


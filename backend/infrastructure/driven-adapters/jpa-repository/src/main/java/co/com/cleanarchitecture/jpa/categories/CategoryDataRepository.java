package co.com.cleanarchitecture.jpa.categories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CategoryDataRepository extends PagingAndSortingRepository<CategoryData, Long>,
        QueryByExampleExecutor<CategoryData> {
}

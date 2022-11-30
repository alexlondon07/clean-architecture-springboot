package co.com.cleanarchitecture.jpa.categories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryDataRepository extends PagingAndSortingRepository<CategoryData, Long>,
        QueryByExampleExecutor<CategoryData> {

    @Transactional
    @Modifying
    @Query("Update CategoryData c SET c.enable=:enable WHERE c.id=:id")
    void enable(@Param("id") Long id, @Param("enable") Boolean enable);
}

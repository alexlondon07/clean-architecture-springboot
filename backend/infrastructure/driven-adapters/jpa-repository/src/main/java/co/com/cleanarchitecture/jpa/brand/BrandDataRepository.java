package co.com.cleanarchitecture.jpa.brand;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface BrandDataRepository extends PagingAndSortingRepository<BrandData, Long>, QueryByExampleExecutor<BrandData> {
}

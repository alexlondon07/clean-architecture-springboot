package co.com.cleanarchitecture.jpa.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserDataRepository extends PagingAndSortingRepository<UserData, Long>, QueryByExampleExecutor<UserData> {
}

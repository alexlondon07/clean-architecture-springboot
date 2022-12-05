package co.com.cleanarchitecture.jpa.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;


public interface UserDataRepository extends JpaRepository<UserData, Long>, QueryByExampleExecutor<UserData> {

    @Query("SELECT u FROM UserData u WHERE u.username = ?1")
    UserData findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}

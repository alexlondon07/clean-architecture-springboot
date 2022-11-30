package co.com.cleanarchitecture.jpa.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import co.com.cleanarchitecture.model.user.User;

public interface UserDataRepository extends JpaRepository<UserData, Long>, QueryByExampleExecutor<UserData> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
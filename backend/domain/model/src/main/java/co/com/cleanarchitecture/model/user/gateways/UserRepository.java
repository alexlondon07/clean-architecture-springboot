package co.com.cleanarchitecture.model.user.gateways;


import java.util.Optional;

import co.com.cleanarchitecture.model.user.User;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
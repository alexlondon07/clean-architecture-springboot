package co.com.cleanarchitecture.model.user.gateways;


import co.com.cleanarchitecture.model.user.User;

public interface UserRepository {

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User save(User user);
}
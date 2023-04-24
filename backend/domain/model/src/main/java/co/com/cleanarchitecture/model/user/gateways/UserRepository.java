package co.com.cleanarchitecture.model.user.gateways;
import java.util.List;

import co.com.cleanarchitecture.model.user.User;

public interface UserRepository {

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User save(User user);
}
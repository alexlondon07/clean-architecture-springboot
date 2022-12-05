package co.com.cleanarchitecture.jpa.user;


import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.com.cleanarchitecture.jpa.helper.AdapterOperations;
import co.com.cleanarchitecture.model.user.User;
import co.com.cleanarchitecture.model.user.gateways.UserRepository;


@Repository
public class UserDataAdapter extends AdapterOperations<User, UserData, Long, UserDataRepository> implements UserRepository {

    public UserDataAdapter(UserDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User findByUsername(String username) {
        return UserData.getUserFromUserData(repository.findByUsername(username));
    }

    @Override
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}

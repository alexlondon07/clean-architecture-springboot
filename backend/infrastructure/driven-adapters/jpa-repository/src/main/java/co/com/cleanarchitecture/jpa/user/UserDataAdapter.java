package co.com.cleanarchitecture.jpa.user;

import java.util.Optional;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import co.com.cleanarchitecture.jpa.helper.AdapterOperations;
import co.com.cleanarchitecture.model.user.User;
import co.com.cleanarchitecture.model.user.gateways.UserRepository;


@Repository
public class UserDataAdapter extends AdapterOperations<User, UserData, Long, UserDataRepository> implements UserRepository {

    public UserDataAdapter(UserDataRepository repository, ObjectMapper mapper) {        /**
     *  Could be use mapper.mapBuilder if your domain model implement builder pattern
     *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
     *  Or using mapper.map with the class of the object model
     */
        super(repository, mapper, d -> mapper.map(d, User.class));
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
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

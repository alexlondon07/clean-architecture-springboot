package co.com.cleanarchitecture.usecase.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.com.cleanarchitecture.model.user.User;
import co.com.cleanarchitecture.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import technicalogs.gateways.LoggerRepository;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository repository;
    private final LoggerRepository logger;

    public List<User> getUsersAll() {
        List<User> list = new ArrayList<>();
        try {
            list = repository.getAll();
            logger.info("Getting users list");
        } catch (Exception ex) {
            logger.error("Error Getting users list -->", ex);
            return Collections.emptyList();
        }
        return list;
    }

    public User findUserById(Long id) {
        try {
            logger.info("Start execution of findUserById method with id " + id);
            return repository.findById(id);
        } catch (Exception ex) {
            logger.error("Error in findUserById method  -->", ex);
            return null;
        }
    }

    public User findByUsername(String username) {
        try {
            logger.info("Start execution of findByUsername method with username " + username);
            return repository.findByUsername(username);
        } catch (Exception ex) {
            logger.error("Error in findByUsername method  -->", ex);
            return null;
        }
    }

    public User save(User user) {
        try {
            return repository.save(user);
        } catch (Exception ex) {
            logger.error("Error in save user method  -->", ex);
            return null;
        }
    }

    public boolean existsByUsername(String username) {
        try {
            logger.info("Start execution of existsByUsername method with username  " + username);
            return repository.existsByUsername(username);
        } catch (Exception ex) {
            logger.error("Error in existsByUsername method -->", ex);
            return false;
        }
    }

    public boolean existsByEmail(String email) {
        try {
            logger.info("Start execution of existsByEmail method with email" + email);
            return repository.existsByEmail(email);
        } catch (Exception ex) {
            logger.error("Error in existsByEmail method -->", ex);
            return false;
        }
    }

}

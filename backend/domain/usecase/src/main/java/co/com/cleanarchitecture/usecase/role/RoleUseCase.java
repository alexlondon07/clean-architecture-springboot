package co.com.cleanarchitecture.usecase.role;

import co.com.cleanarchitecture.model.role.Role;
import co.com.cleanarchitecture.model.role.gateways.RoleRepository;
import lombok.RequiredArgsConstructor;
import technicalogs.gateways.LoggerRepository;

@RequiredArgsConstructor
public class RoleUseCase {

    private final RoleRepository repository;
    private final LoggerRepository logger;


    public Role findByName(String name) {
        try {
            logger.info("Start execution of findByName method to obtain the role name  " + name);
            return repository.findByName(name);
        } catch (Exception ex) {
            logger.error("Error in findByName method - RoleUseCase -->", ex);
            return null;
        }
    }
}



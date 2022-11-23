package co.com.cleanarchitecture.model.role.gateways;

import java.util.Optional;

import javax.management.relation.Role;

public interface RoleRepository {

    Optional<Role> findByName(Role name);
}

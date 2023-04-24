package co.com.cleanarchitecture.model.role.gateways;

import co.com.cleanarchitecture.model.role.Role;


public interface RoleRepository {

    Role findByName(String name);
}

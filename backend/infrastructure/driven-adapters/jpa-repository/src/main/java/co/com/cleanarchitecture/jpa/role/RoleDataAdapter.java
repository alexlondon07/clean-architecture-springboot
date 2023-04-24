package co.com.cleanarchitecture.jpa.role;


import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import co.com.cleanarchitecture.jpa.helper.AdapterOperations;
import co.com.cleanarchitecture.model.role.Role;
import co.com.cleanarchitecture.model.role.gateways.RoleRepository;

@Repository
public class RoleDataAdapter extends AdapterOperations<Role, RoleData, Long, RoleDataRepository> implements RoleRepository {

        public RoleDataAdapter(RoleDataRepository repository, ObjectMapper mapper) {
                super(repository, mapper, d -> mapper.map(d, Role.class));
        }

        @Override
        public Role findByName(String name) {
                return repository.findByName(name);
        }

}

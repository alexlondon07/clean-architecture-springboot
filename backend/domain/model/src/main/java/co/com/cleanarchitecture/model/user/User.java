package co.com.cleanarchitecture.model.user;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.com.cleanarchitecture.model.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String lastName;
    private boolean enable = true;
    private String password;
    private Set<Role> roles = new HashSet<>();
}

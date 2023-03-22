package co.com.cleanarchitecture.jpa.user;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import co.com.cleanarchitecture.jpa.role.RoleData;
import co.com.cleanarchitecture.model.role.Role;
import co.com.cleanarchitecture.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Size(max = 60)
    @Column(name = "last_name")
    private String lastName;

    private boolean enable = true;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleData> roles = new HashSet<>();

    public static User getUserFromUserData(UserData userData) {

        if (userData==null)
            return null;

        Set<Role> roles = new HashSet<>(userData.getRoles());
        return User.builder()
                .id(userData.getId())
                .name(userData.getName())
                .username(userData.getUsername())
                .lastName(userData.getLastName())
                .password(userData.getPassword())
                .email(userData.getEmail())
                .enable(userData.isEnable())
                .roles(roles)
                .build();
    }

    public static List<User> convertUserDataLstToUserList(List<UserData> userDataList) {

        if (userDataList.isEmpty())
            return new ArrayList<>();

        List<User> userList = new ArrayList<>();

        userDataList.forEach( userData-> userList.add(getUserFromUserData(userData)));

        return userList;
    }
}

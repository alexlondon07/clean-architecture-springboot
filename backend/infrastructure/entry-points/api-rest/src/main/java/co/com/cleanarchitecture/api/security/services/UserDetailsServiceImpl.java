package co.com.cleanarchitecture.api.security.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.com.cleanarchitecture.model.user.User;
import co.com.cleanarchitecture.usecase.user.UserUseCase;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private  UserUseCase useCase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = useCase.findByUsername(username);
        if (user==null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return UserDetailsImpl.build(user);
    }

}
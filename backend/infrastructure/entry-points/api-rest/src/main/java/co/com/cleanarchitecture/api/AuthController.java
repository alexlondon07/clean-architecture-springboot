package co.com.cleanarchitecture.api;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cleanarchitecture.api.dto.JwtResponse;
import co.com.cleanarchitecture.api.dto.LoginRequest;
import co.com.cleanarchitecture.api.dto.MessageResponse;
import co.com.cleanarchitecture.api.dto.SignupRequest;
import co.com.cleanarchitecture.api.security.jwt.JwtUtils;
import co.com.cleanarchitecture.api.security.services.UserDetailsImpl;
import co.com.cleanarchitecture.model.role.ERole;
import co.com.cleanarchitecture.model.role.Role;
import co.com.cleanarchitecture.model.user.User;
import co.com.cleanarchitecture.usecase.role.RoleUseCase;
import co.com.cleanarchitecture.usecase.user.UserUseCase;
import lombok.AllArgsConstructor;
import technicalogs.gateways.LoggerRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthController {

    private final UserUseCase userUseCase;
    private final RoleUseCase roleUseCase;
    private final LoggerRepository logger;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userUseCase.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userUseCase.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = User.builder()
                .name(signUpRequest.getName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .enable(signUpRequest.isEnable())
                .username(signUpRequest.getUsername()).build();

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles==null) {
            Role userRole = roleUseCase.findByName(ERole.ROLE_USER.toString());
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleUseCase.findByName(ERole.ROLE_ADMIN.toString());
                        roles.add(adminRole);
                        break;

                    case "mod":
                        Role modRole = roleUseCase.findByName(ERole.ROLE_ADMIN.ROLE_MODERATOR.toString());
                        roles.add(modRole);
                        break;

                    default:
                        Role userRole = roleUseCase.findByName(ERole.ROLE_USER.toString());
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userUseCase.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
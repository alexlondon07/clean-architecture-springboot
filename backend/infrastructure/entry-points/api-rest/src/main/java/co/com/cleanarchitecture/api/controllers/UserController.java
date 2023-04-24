package co.com.cleanarchitecture.api.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cleanarchitecture.model.user.User;
import co.com.cleanarchitecture.usecase.user.UserUseCase;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import technicalogs.gateways.LoggerRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;
    private final LoggerRepository logger;

    @GetMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userUseCase.getUsersAll(), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<User> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userUseCase.findUserById(id), HttpStatus.OK);
    }

    /*@GetMapping("/page/{page}/{size}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Page<Category>> getAllPageable(@PathVariable Integer page, @PathVariable Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Category> categoryList = beanCategoryUseCase.getCategories();
        Page<Category> pages = new PageImpl<>(categoryList, pageable, categoryList.size());
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }*/

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("cleanArchitectureJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}

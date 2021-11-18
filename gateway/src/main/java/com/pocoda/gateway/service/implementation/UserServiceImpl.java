package com.pocoda.gateway.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pocoda.gateway.model.User;
import com.pocoda.gateway.model.exception.AuthorizationException;
import com.pocoda.gateway.model.exception.NotFoundException;
import com.pocoda.gateway.model.request.UserLoginRequest;
import com.pocoda.gateway.model.request.UserRegistrationRequest;
import com.pocoda.gateway.model.response.UserAuthorizationResponse;
import com.pocoda.gateway.service.UserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.pocoda.gateway.helper.AuthorizationUtils.JWT_SECRET;
import static com.pocoda.gateway.helper.AuthorizationUtils.USER_ID;
import static com.pocoda.gateway.helper.PasswordUtils.encodePassword;
import static com.pocoda.gateway.helper.PasswordUtils.isPasswordMatching;
import static java.time.temporal.ChronoUnit.HOURS;

@Service
public class UserServiceImpl implements UserService {
    private List<User> users;

    public UserServiceImpl() {
        users = Collections.singletonList(new User(1l, "Adam", "Adminowicz", "admin", encodePassword("admin")));
    }

    @Override
    public Optional<User> findById(long id) {
        return users.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    @Override
    public UserAuthorizationResponse login(UserLoginRequest request) {
        User user = findUserUsername(request.getUsername());
        validateLogin(user, request.getPassword());
        String token = generateJwt(user);
        return UserAuthorizationResponse.builder()
                .token(token)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .build();
    }

    @Override
    public UserAuthorizationResponse register(UserRegistrationRequest request) {
        User user = saveUser(request);
        String token = generateJwt(user);
        return UserAuthorizationResponse.builder()
                .token(token)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .build();
    }

    private User saveUser(UserRegistrationRequest request) {
        String encodedPassword = encodePassword(request.getPassword());
        User user = new User((long) users.size(), request.getFirstName(), request.getLastName(), request.getUsername(), encodedPassword);
        users.add(user);
        return user;
    }


    private User findUserUsername(String username) {
        return users.stream().filter(item -> item.getUsername().equals(username)).findFirst()
                .orElseThrow(() -> new NotFoundException("User doesn't exist"));
    }

    private void validateLogin(User userEntity, String password) {
        if (!isPasswordMatching(password, userEntity.getPassword())) {
            throw new AuthorizationException("User provided incorrect login or password");
        }
    }

    private String generateJwt(User user) {
        return JWT.create()
                .withClaim(USER_ID, user.getId().toString())
                .withExpiresAt(Date.from(Instant.now().plus(24, HOURS)))
                .sign(Algorithm.HMAC256(JWT_SECRET));
    }
}

package com.pocoda.gateway.service;

import com.pocoda.gateway.model.User;
import com.pocoda.gateway.model.request.UserLoginRequest;
import com.pocoda.gateway.model.request.UserRegistrationRequest;
import com.pocoda.gateway.model.response.UserAuthorizationResponse;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(long id);

    UserAuthorizationResponse login(UserLoginRequest request);

    UserAuthorizationResponse register(UserRegistrationRequest request);
}

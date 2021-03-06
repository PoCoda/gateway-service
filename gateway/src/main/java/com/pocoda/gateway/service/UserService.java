package com.pocoda.gateway.service;

import com.pocoda.gateway.model.User;
import com.pocoda.gateway.model.request.UserLoginRequest;
import com.pocoda.gateway.model.request.UserRegistrationRequest;
import com.pocoda.gateway.model.response.UserAuthorizationResponse;
import com.pocoda.gateway.model.response.UserResponse;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    UserAuthorizationResponse login(UserLoginRequest request);

    UserAuthorizationResponse register(UserRegistrationRequest request);

    UserResponse getDetails(Long userId);

    UserResponse updateCity(Long userId, String city);
}

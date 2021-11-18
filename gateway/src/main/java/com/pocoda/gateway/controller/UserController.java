package com.pocoda.gateway.controller;


import com.pocoda.gateway.model.request.UserLoginRequest;
import com.pocoda.gateway.model.request.UserRegistrationRequest;
import com.pocoda.gateway.model.response.UserAuthorizationResponse;
import com.pocoda.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    ResponseEntity<UserAuthorizationResponse> register(@RequestBody @Valid UserRegistrationRequest request) {
        var result = userService.register(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    ResponseEntity<UserAuthorizationResponse> login(@RequestBody @Valid UserLoginRequest request) {
        var result = userService.login(request);
        return ResponseEntity.ok(result);
    }
}

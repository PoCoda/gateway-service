package com.pocoda.gateway.controller;


import com.pocoda.gateway.anotation.UserAuthority;
import com.pocoda.gateway.model.request.UserLoginRequest;
import com.pocoda.gateway.model.request.UserRegistrationRequest;
import com.pocoda.gateway.model.response.UserAuthorizationResponse;
import com.pocoda.gateway.model.response.UserResponse;
import com.pocoda.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

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

    @GetMapping
    @UserAuthority
    ResponseEntity<UserResponse> details(Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        var result = userService.getDetails(userId);
        return ResponseEntity.ok(result);
    }
}

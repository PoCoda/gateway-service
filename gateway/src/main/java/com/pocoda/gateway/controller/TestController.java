package com.pocoda.gateway.controller;

import com.pocoda.gateway.anotation.UserAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    @UserAuthority
    public String test() {
        return "Hello PoCoda gateway";
    }
}

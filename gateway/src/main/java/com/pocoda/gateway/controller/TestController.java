package com.pocoda.gateway.controller;

import com.pocoda.gateway.anotation.UserAuthority;
import com.pocoda.gateway.service.web.AggregatorWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private AggregatorWebService aggregatorWebService;

    @GetMapping("/test")
    @UserAuthority
    public String test() {
        return "Hello PoCoda gateway";
    }

    @GetMapping("/test/aggregator")
    @UserAuthority
    public String testAggregator() {
        return aggregatorWebService.test();
    }

    @GetMapping("/test/aggregator/weather")
    @UserAuthority
    public String testAggregatorWeather() {
        return aggregatorWebService.testWeather();
    }
}

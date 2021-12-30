package com.pocoda.gateway.controller;


import com.pocoda.gateway.model.response.InfoResponse;
import com.pocoda.gateway.service.web.AggregatorWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("info")
public class InfoController {
    @Autowired
    private AggregatorWebService aggregatorWebService;

    @GetMapping
    public ResponseEntity<InfoResponse> getInfo(@RequestParam(value = "city", required = false) String city) {
        return ResponseEntity.ok(aggregatorWebService.getInfo(city));
    }
}

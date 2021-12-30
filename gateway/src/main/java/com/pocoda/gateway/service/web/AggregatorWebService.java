package com.pocoda.gateway.service.web;

import com.pocoda.gateway.model.response.InfoResponse;

public interface AggregatorWebService {
    String test();
    String testWeather();

    InfoResponse getInfo(String city);
}

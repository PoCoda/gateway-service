package com.pocoda.gateway.service.web.implementation;

import com.pocoda.gateway.model.response.InfoResponse;
import com.pocoda.gateway.service.web.AggregatorWebService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AggregatorWebServiceImpl implements AggregatorWebService {
    @Autowired
    private RestTemplate restTemplate;

    private String ulr = "http://aggregator-service/";

    @Override
    public String test() {
        return restTemplate.getForObject(ulr + "test", String.class);
    }

    @Override
    public String testWeather()  {
        return restTemplate.getForObject(ulr + "test/weather", String.class);
    }

    @Override
    public InfoResponse getInfo(String city) {
        String infoUrl = ulr + "/info?city={0}";
        return this.restTemplate.getForObject(infoUrl, InfoResponse.class, city);
    }
}

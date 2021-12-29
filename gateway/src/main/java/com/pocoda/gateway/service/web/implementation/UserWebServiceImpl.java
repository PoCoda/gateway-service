package com.pocoda.gateway.service.web.implementation;

import com.pocoda.gateway.model.UpdateUserCityWebRequest;
import com.pocoda.gateway.model.User;
import com.pocoda.gateway.service.web.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserWebServiceImpl implements UserWebService {
    @Autowired
    private RestTemplate restTemplate;

    private String ulr = "http://user-service/user/";

    @Override
    public User getById(Long id) {
        return restTemplate.getForObject(ulr + id, User.class);
    }

    @Override
    public User getByUsername(String username) {
        return restTemplate.getForObject(ulr +  "username/" + username, User.class);
    }

    @Override
    public User create(User user) {
        HttpEntity<User> requestEntity = new HttpEntity(user, new HttpHeaders());
        HttpEntity<User> response = restTemplate.exchange(ulr, HttpMethod.POST, requestEntity, User.class);
        return response.getBody();
    }

    @Override
    public User updateCity(UpdateUserCityWebRequest request) {
        HttpEntity<User> requestEntity = new HttpEntity(request, new HttpHeaders());
        HttpEntity<User> response = restTemplate.exchange(ulr + "city", HttpMethod.PUT, requestEntity, User.class);
        return response.getBody();
    }
}

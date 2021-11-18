package com.pocoda.gateway.service.web;

import com.pocoda.gateway.model.User;

public interface UserWebService {
    User getById(Long id);

    User getByUsername(String username);

    User create(User user);
}

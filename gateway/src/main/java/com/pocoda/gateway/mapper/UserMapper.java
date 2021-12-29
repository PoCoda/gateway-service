package com.pocoda.gateway.mapper;

import com.pocoda.gateway.model.User;
import com.pocoda.gateway.model.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse userToUserResponse(User user){
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .city(user.getCity())
                .build();
    }
}

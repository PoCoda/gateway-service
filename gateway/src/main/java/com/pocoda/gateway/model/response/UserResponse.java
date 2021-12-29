package com.pocoda.gateway.model.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserResponse {
    public String firstName;
    public String lastName;
    public String username;
    public String city;
}

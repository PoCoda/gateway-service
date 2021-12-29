package com.pocoda.gateway.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdateUserCityWebRequest {
    public Long userId;
    public String city;
}

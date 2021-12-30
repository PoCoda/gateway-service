package com.pocoda.gateway.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    private String location;
    private double temp;
    private String condition;
}

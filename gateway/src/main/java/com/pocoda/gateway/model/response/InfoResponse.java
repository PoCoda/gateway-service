package com.pocoda.gateway.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoResponse {
    private WeatherResponse weather;
}

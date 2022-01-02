package com.pocoda.gateway.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollutionResponse {
    private PollutionModel pm10;
    private PollutionModel pm25;
    boolean matchesNorms = true;
}

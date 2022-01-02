package com.pocoda.gateway.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollutionModel {
	private PollutionType type;
	private Double value;
	private Double percentage;
}

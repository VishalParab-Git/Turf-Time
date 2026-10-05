package com.turftime.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurfSportUpdateDTO {


	@NotNull(message="turf id is required")
	private Long turfId;
	
	@NotNull(message="sport id is required")
	private Long turfSportId;
	
	@NotNull(message="price is required")
	private BigDecimal price;
	
}

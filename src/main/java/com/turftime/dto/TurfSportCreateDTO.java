package com.turftime.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TurfSportCreateDTO {

	@NotNull(message="turf id is required")
	private Long turfId;
	
	@NotNull(message="sport id is required")
	private Long sportId;
	
	@NotNull(message="price is required")
	private BigDecimal price;
	
}

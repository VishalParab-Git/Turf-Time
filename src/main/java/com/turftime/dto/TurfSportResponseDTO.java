package com.turftime.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TurfSportResponseDTO {

	private Long id;
	
	private Long turfId;
	
	private Long sportId;
	
	private BigDecimal price;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
}

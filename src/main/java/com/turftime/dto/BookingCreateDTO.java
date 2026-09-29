package com.turftime.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingCreateDTO {

	private Long turfId;
	
	private Long sportId;
	
	private LocalDate bookingDate;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private BigDecimal amount;
	
}

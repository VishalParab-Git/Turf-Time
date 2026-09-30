package com.turftime.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingResponseDTO {

	private Long id;
	
	private String bookingNumber;
	
	private String userName;
	
	private String turfName;
	
	private String sportName;
	
	private String resourceName;
	
	private LocalDate bookingDate;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private BigDecimal amount;
	
	private String status;
	
	private LocalDateTime createdAt;
	
}

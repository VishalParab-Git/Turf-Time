package com.turftime.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TurfResponseDTO {

	private Long id;
	
	private Long ownerId;
	
	private String name;
	
	private String description;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String pincode;
	
	private LocalTime openingTime;
	
	private LocalTime closingTime;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	
	
}

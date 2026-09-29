package com.turftime.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TurfResourceResponseDTO {

	private Long id;
	
	private Long turfId;
	
	private String resourceName;
	
	private String resourceType;
	
	private Integer capacity;
	
	private String status;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
}

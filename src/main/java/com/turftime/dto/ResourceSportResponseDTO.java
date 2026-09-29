package com.turftime.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResourceSportResponseDTO {

	private  Long id;
	
	private Long resourceId;
	
	private String resourceName;
	
	private String turfSportName;
	
	private LocalDateTime createdAt;
}

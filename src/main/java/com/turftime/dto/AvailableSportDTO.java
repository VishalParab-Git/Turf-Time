package com.turftime.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSportDTO {

	private Long turfId;
	
	private String turfName;
	
	private String sportName;
	
	private Long resourceId;
	
	private String resourceName;
	
}

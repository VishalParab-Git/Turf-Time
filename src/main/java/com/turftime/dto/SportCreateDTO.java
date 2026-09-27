package com.turftime.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SportCreateDTO {
	
	@NotBlank(message="Sport name is required")
	@Size(min=4, max=200)
	private String name;
	
	
	@NotBlank(message="Enter Small description")
	@Size(max=500)
	private String description;
	

}

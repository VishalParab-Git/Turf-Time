package com.turftime.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurfResourceUpdateDTO {
	
	@NotBlank(message="Resource Name must Required")
	@Size(max=200)
	private String resourceName;
	
	@NotBlank(message="Resource Type must be required")
	private String resourceType;
	
	@NotNull(message="please Enter Capacity")
	private Integer capacity;
	

}

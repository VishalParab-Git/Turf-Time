package com.turftime.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TurfCreateDTO {

	@NotBlank(message="Turf name must Required")
	private String name;
	
	@NotBlank(message="description is not blank")
	private String description;
	
	@NotBlank(message = "address must be enter")
	private String address;

	@NotBlank(message = "city must be enter")
	private String city;

	@NotBlank(message = "state must be enter")
	private String state;

	@NotBlank(message = "pincode must be enter")
	@Size(min = 6, max = 6, message = "Pincode must be exactly 6 characters")
	@Pattern(regexp = "^[1-9][0-9]{5}$", message = "Pincode must be a valid 6-digit number and cannot start with 0")
	private String pincode;
	
	@NotNull(message = "Opening Time must be enter")
	private LocalTime openingTime;
	
	@NotNull(message = "Closing Time must be enter")
	private LocalTime closingTime;

}

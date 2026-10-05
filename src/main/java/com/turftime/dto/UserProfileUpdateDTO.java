package com.turftime.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileUpdateDTO {

	@NotBlank(message = "first name must be enter")
	private String firstName;

	@NotBlank(message = "last name must be enter")
	private String lastName;

	@NotBlank(message = "phone must be enter ")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain exactly 10 digits")
	private String phone;

	@NotBlank(message = "address must be enter")
	private String address;

	@NotBlank(message = "city must be enter")
	private String city;

	@NotBlank(message = "state must be enter")
	private String state;

	@NotBlank(message = "country must be enter")
	private String country;

	@NotBlank(message = "pincode must be enter")
	@Size(min = 6, max = 6, message = "Pincode must be exactly 6 characters")
	@Pattern(regexp = "^[1-9][0-9]{5}$", message = "Pincode must be a valid 6-digit number and cannot start with 0")
	private String pincode;

	
}

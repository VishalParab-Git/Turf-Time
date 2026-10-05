package com.turftime.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurfOwnerProfileUpdateDTO {
	
	@NotBlank(message="Owner name must be enter")
	private String ownerName;
	
	@NotBlank(message = "phone must be enter ")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain exactly 10 digits")
	private String phone;
	
	@NotBlank(message="Business name must be enter")
	private String businessName;

}

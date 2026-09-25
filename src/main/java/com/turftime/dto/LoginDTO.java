package com.turftime.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginDTO {

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Size(max = 100, message = "Email must not exceed 100 characters")
	private String username;

	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 200, message = "Password must be between 8 and 200 characters")
	private String password;
}

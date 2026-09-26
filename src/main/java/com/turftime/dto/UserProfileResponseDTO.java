package com.turftime.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserProfileResponseDTO {
	
	private Long id;

	private Long userId;			

	private String firstName;

	private String lastName;

	private String phone;

	private String address;

	private String city;

	private String state;

	private String country;

	private String pincode;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;


	

}

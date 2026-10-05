package com.turftime.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurfOwnerResponseDTO {

	private Long id;
	
	private Long userId;
	
	private String ownerName;
	
	private String phone;

	private String status;
	
	private String businessName;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
}

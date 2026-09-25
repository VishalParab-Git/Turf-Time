package com.turftime.dto;

import java.time.LocalDateTime;

import com.turftime.enums.UserRole;
import com.turftime.enums.UserStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserResponseDTO {
	
	 private Long id;
	    private String email;
	    private UserRole role;
	    private UserStatus status;
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;

}

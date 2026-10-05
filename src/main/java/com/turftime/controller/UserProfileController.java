package com.turftime.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.UserProfileCreateDTO;
import com.turftime.dto.UserProfileResponseDTO;
import com.turftime.dto.UserProfileUpdateDTO;
import com.turftime.service.UserProfileService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user-profile")
@RequiredArgsConstructor
public class UserProfileController {
	
	private final UserProfileService userProfileService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('PLAYER','ADMIN')")
	public ResponseEntity<UserProfileResponseDTO> createProfile( @Valid @RequestBody UserProfileCreateDTO dto) {
		return ResponseEntity.ok(userProfileService.createProfile(dto));
		 
	}
	
	
	@GetMapping
	@PreAuthorize("hasAnyRole('PLAYER','ADMIN')")
	public ResponseEntity<UserProfileResponseDTO> getProfile() {
		
		return ResponseEntity.ok(userProfileService.getProfile());
		
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('PLAYER','ADMIN')")
	public ResponseEntity<UserProfileResponseDTO> updateUserProfile(@Valid @RequestBody UserProfileUpdateDTO user) {
		
		return ResponseEntity.ok(userProfileService.updateUserProfile(user));
	}
	
	

}

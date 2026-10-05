package com.turftime.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.ChangePasswordRequestDTO;
import com.turftime.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/users/me")
@AllArgsConstructor
public class UserController {

	private final UserService userService;
	
	//Change password
	@PutMapping("/password")
	@PreAuthorize("hasAnyRole('PLAYER','ADMIN')")
	public ResponseEntity<String> changePassword( @Valid @RequestBody ChangePasswordRequestDTO dto) {
		
		return ResponseEntity.ok(userService.changePassword(dto));
	}
	
	//delete current user account
	@DeleteMapping("/delete")
	@PreAuthorize("hasAnyRole('PLAYER','ADMIN')")
	public void deleteMyAccount() {
		userService.deleteMyAccount();
	}
	
	
}

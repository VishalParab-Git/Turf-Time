package com.turftime.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.LoginDTO;
import com.turftime.dto.LoginResponseDTO;
import com.turftime.dto.UserRegisterDTO;
import com.turftime.dto.UserResponseDTO;
import com.turftime.security.AuthService;
import com.turftime.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;
	private final AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> registerUser( @Valid @RequestBody UserRegisterDTO dto) {
		return ResponseEntity.ok(userService.registerUser(dto));
	}
	
	@PostMapping("/login")
	public LoginResponseDTO login( @Valid @RequestBody LoginDTO request) {
		
		String token= authService.login(
				request.getUsername(),
                request.getPassword()
                );
		
		return new LoginResponseDTO(token);
		
	}
}

package com.turftime.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.TurfOwnerCreateDTO;
import com.turftime.dto.TurfOwnerResponseDTO;
import com.turftime.service.TurfOwnerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/owners")
@AllArgsConstructor
public class TurfOwnerController {

	private final TurfOwnerService ownerService;
	
	@PostMapping("/register")
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<TurfOwnerResponseDTO> createOwner(@Valid @RequestBody TurfOwnerCreateDTO dto) {
		
		return ResponseEntity.ok(ownerService.createOwner(dto));
		
	}
	
	@GetMapping
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<TurfOwnerResponseDTO> currentOwner(){
		return ResponseEntity.ok(ownerService.currentOwner());
	}
}

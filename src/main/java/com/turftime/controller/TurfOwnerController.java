package com.turftime.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.TurfOwnerCreateDTO;
import com.turftime.dto.TurfOwnerProfileUpdateDTO;
import com.turftime.dto.TurfOwnerResponseDTO;
import com.turftime.service.TurfOwnerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/owners")
@AllArgsConstructor
public class TurfOwnerController {

	private final TurfOwnerService ownerService;
	
	//Create Turf Owner Account
	@PostMapping("/register")
	@PreAuthorize("hasAnyRole('PLAYER','ADMIN')")
	public ResponseEntity<TurfOwnerResponseDTO> createOwner(@Valid @RequestBody TurfOwnerCreateDTO dto) {
		
		return ResponseEntity.ok(ownerService.createOwner(dto));
		
	}
	
	//Get Current Owner Information
	@GetMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<TurfOwnerResponseDTO> currentOwner(){
		return ResponseEntity.ok(ownerService.currentOwner());
	}
	
	//Update Current Turf Owner Profile
	@PutMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<TurfOwnerResponseDTO> updateTurfOwnerProfile( @Valid @RequestBody TurfOwnerProfileUpdateDTO dto) {
		
		return ResponseEntity.ok(ownerService.updateTurfOwnerProfile(dto));
	}
	
	//Delete Turf Owner Account
	@DeleteMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public void deleteCurrentTurfOwnerAccount() {
		
		ownerService.deleteTurfOwnerAccount();
	}
}

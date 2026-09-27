package com.turftime.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.SportCreateDTO;
import com.turftime.dto.SportResponseDTO;
import com.turftime.service.SportsService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/sports")
@AllArgsConstructor
public class SportController {

	private final SportsService sportService;
	
	@PostMapping
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<SportResponseDTO> createSport( @Valid @RequestBody SportCreateDTO dto){
		
		return ResponseEntity.ok(sportService.createSport(dto));
	}
	
	@GetMapping
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<List<SportResponseDTO>> getAllSports(){
		return ResponseEntity.ok(sportService.getAllSport());
	}
}

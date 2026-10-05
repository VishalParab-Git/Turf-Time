package com.turftime.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.SportCreateDTO;
import com.turftime.dto.SportResponseDTO;
import com.turftime.dto.SportUpdateDTO;
import com.turftime.service.SportsService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/sports")
@AllArgsConstructor
public class SportController {

	private final SportsService sportService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<SportResponseDTO> createSport( @Valid @RequestBody SportCreateDTO dto){
		
		return ResponseEntity.ok(sportService.createSport(dto));
	}
	
	@GetMapping
	@PreAuthorize("hasAnyRole('PLAYER','TURF_OWNER','ADMIN')")
	public ResponseEntity<List<SportResponseDTO>> getAllSports(){
		return ResponseEntity.ok(sportService.getAllSport());
	}
	
	@PutMapping("/{sportId}")
	@PreAuthorize("hasAnyRole('PLAYER','TURF_OWNER','ADMIN')")
	public ResponseEntity<SportResponseDTO> updateSportById(@PathVariable Long sportId, @RequestBody SportUpdateDTO dto){
		
		return ResponseEntity.ok(sportService.updateSport(sportId, dto));
	}
	
	
	@DeleteMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<String> deleteAllSport() {
		
		return ResponseEntity.ok(sportService.deleteAllSport());
	}
}

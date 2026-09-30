package com.turftime.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.TurfCreateDTO;
import com.turftime.dto.TurfResponseDTO;
import com.turftime.service.TurfsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/turfs")
@AllArgsConstructor
public class TurfController {

	private final TurfsService turfService;
	
	@PostMapping
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<TurfResponseDTO> createTurf(@RequestBody TurfCreateDTO dto) {
		
		return ResponseEntity.ok(turfService.createTurf(dto));
	}
	
	@GetMapping
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<TurfResponseDTO> getTurf() {
		
		return ResponseEntity.ok(turfService.getTurf()) ;
	}
	
	@GetMapping("/city-turfs")
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<List<TurfResponseDTO>> getAllturfsByCityAndState(@RequestParam String city, @RequestParam String state){
		
		return ResponseEntity.ok(turfService.findAllTurfsByCityorState(city, state));
	}
}

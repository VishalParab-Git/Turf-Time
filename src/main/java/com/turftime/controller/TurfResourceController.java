package com.turftime.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.TurfResourceCreateDTO;
import com.turftime.dto.TurfResourceResponseDTO;
import com.turftime.service.TurfResourceService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/turf-resources")
@AllArgsConstructor
public class TurfResourceController {

	private final TurfResourceService turfResourceService;
	
	
	@PostMapping
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<TurfResourceResponseDTO> createTurfResource(@Valid @RequestBody TurfResourceCreateDTO dto) {
		
		return ResponseEntity.ok(turfResourceService.createTurfResource(dto));
	}
	
	@GetMapping("/{turfId}/{turfResourceId}")
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<TurfResourceResponseDTO> getTurfResourceById(@PathVariable Long turfId, @PathVariable Long turfResourceId) {
		
		return ResponseEntity.ok(turfResourceService.getTurfResourceById(turfId, turfResourceId));
	}
	
	@GetMapping("/{turfId}")
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<List<TurfResourceResponseDTO>> getAllTurfResource(@PathVariable Long turfId) {
		
		return ResponseEntity.ok(turfResourceService.getAllTurfResource(turfId));
	}
}

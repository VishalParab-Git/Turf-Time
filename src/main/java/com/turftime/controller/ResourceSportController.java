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

import com.turftime.dto.ResourceSportCreateDTO;
import com.turftime.dto.ResourceSportResponseDTO;
import com.turftime.service.ResourceSportService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/resource-sports")
@AllArgsConstructor
public class ResourceSportController {
	
	private final ResourceSportService resourceSportService;

	@PostMapping
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<ResourceSportResponseDTO> createResourceSport(@Valid @RequestBody ResourceSportCreateDTO dto) {
		
		return ResponseEntity.ok(resourceSportService.createResourceSport(dto));
	}
	
	@GetMapping("/{turfId}/{resourceId}")
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<List<ResourceSportResponseDTO>> getAllResourceSportsById( @PathVariable Long turfId, @PathVariable Long resourceId) {
		
		return ResponseEntity.ok(resourceSportService.getResourceSportById(turfId, resourceId));
	}
}

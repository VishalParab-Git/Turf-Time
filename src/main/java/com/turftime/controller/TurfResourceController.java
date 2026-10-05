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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.TurfResourceCreateDTO;
import com.turftime.dto.TurfResourceResponseDTO;
import com.turftime.dto.TurfResourceUpdateDTO;
import com.turftime.service.TurfResourceService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/turf-resources")
@AllArgsConstructor
public class TurfResourceController {

	private final TurfResourceService turfResourceService;
	
	
	@PostMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<TurfResourceResponseDTO> createTurfResource(@Valid @RequestBody TurfResourceCreateDTO dto) {
		
		return ResponseEntity.ok(turfResourceService.createTurfResource(dto));
	}
	
	@GetMapping("/{turfId}/{turfResourceId}")
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<TurfResourceResponseDTO> getTurfResourceById(@PathVariable Long turfId, @PathVariable Long turfResourceId) {
		
		return ResponseEntity.ok(turfResourceService.getTurfResourceById(turfId, turfResourceId));
	}
	
	@GetMapping("/{turfId}")
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN','PLAYER')")
	public ResponseEntity<List<TurfResourceResponseDTO>> getAllTurfResource(@PathVariable Long turfId) {
		
		return ResponseEntity.ok(turfResourceService.getAllTurfResource(turfId));
	}
	
	
	@PutMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<TurfResourceResponseDTO> updateTurfResource(@RequestParam Long turfId, @RequestParam Long turfResourceId , @RequestBody TurfResourceUpdateDTO dto) {
		
		return ResponseEntity.ok(turfResourceService.updateTurfResource(turfId, turfResourceId, dto));
	}
	
	@DeleteMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<String> deleteResource(@RequestParam Long turfId, @RequestParam Long turfResourceId  ) {
		
		return ResponseEntity.ok(turfResourceService.deleteTurfResource(turfId, turfResourceId));
	}
	
}

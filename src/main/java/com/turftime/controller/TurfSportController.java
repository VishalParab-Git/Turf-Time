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

import com.turftime.dto.TurfSportCreateDTO;
import com.turftime.dto.TurfSportResponseDTO;
import com.turftime.dto.TurfSportUpdateDTO;
import com.turftime.service.TurfSportsService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/turf-sports")
@AllArgsConstructor
public class TurfSportController {

	private final TurfSportsService turfSportsService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<TurfSportResponseDTO> createTurfSport( @Valid @RequestBody TurfSportCreateDTO dto) {
		
		return ResponseEntity.ok(turfSportsService.createTurfSports(dto));
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN','PLAYER')")
	public ResponseEntity<List<TurfSportResponseDTO>> getAllTurfSports(@PathVariable Long id){
		
		return ResponseEntity.ok(turfSportsService.getAllTurfSport(id));
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<TurfSportResponseDTO> updateTurfSportPrice(@RequestBody TurfSportUpdateDTO dto) {
		return ResponseEntity.ok(turfSportsService.updateTurfSportPrice(dto));
	}
	
	@DeleteMapping("/{turfId}/{turfSportId}")
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public void deleteTurfSport( @PathVariable Long turfId , @PathVariable Long turfSportId) {
		
		turfSportsService.deleteTurfSport(turfId, turfSportId);
	}
	

	@DeleteMapping("/{turfId}")
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<String> deleteTurfSport( @PathVariable Long turfId) {
		
		return ResponseEntity.ok(turfSportsService.deleteAllTurfSports(turfId));
	}
	
}

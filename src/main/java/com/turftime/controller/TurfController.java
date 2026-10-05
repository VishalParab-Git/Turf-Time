package com.turftime.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.TurfCreateDTO;
import com.turftime.dto.TurfResponseDTO;
import com.turftime.dto.TurfUpdateDTO;
import com.turftime.service.TurfsService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/turfs")
@AllArgsConstructor
public class TurfController {

	private final TurfsService turfService;
	
	//create Turf Account
	@PostMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<TurfResponseDTO> createTurf(@RequestBody TurfCreateDTO dto) {
		
		return ResponseEntity.ok(turfService.createTurf(dto));
	}
	
	//get Turf
	@GetMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<List<TurfResponseDTO>> getMyTurf() {
		
		return ResponseEntity.ok(turfService.getMyTurf()) ;
	}
	
	//Get All Turfs in City 
	@GetMapping("/city-turfs")
	@PreAuthorize("hasAnyRole('PLAYER','TURF_OWNER','ADMIN')")
	public ResponseEntity<List<TurfResponseDTO>> getAllturfsByCityAndState(@RequestParam String city, @RequestParam String state){
		
		return ResponseEntity.ok(turfService.findAllTurfsByCityorState(city, state));
	}
	
	//Update Turf Account
	@PutMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public ResponseEntity<TurfResponseDTO> updateTurfAccount(@RequestParam Long turfId, @Valid @RequestBody TurfUpdateDTO dto) {
		
		return ResponseEntity.ok(turfService.updateTurf(turfId, dto));
	}
	
	//delete Turf
	@DeleteMapping
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
	public void deleteTurfAccount(@RequestParam Long turfId) {
		
		turfService.deleteTurfAccount(turfId);
	}
}

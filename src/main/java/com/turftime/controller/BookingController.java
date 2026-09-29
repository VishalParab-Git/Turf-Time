package com.turftime.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.BookingCreateDTO;
import com.turftime.dto.BookingResponseDTO;
import com.turftime.service.BookingService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/bookings")
@AllArgsConstructor
public class BookingController {

	private final BookingService bookingService;
	
	@PostMapping
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<BookingResponseDTO> createBooking( @Valid @RequestBody BookingCreateDTO dto) {
		
		return ResponseEntity.ok(bookingService.createBooking(dto));
	}
	
	public ResponseEntity<List<BookingResponseDTO>> getAllUserBookings(){
		
		return ResponseEntity.ok(bookingService.getAllBookings());
	}
	
}

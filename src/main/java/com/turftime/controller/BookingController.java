package com.turftime.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.AvailableSportDTO;
import com.turftime.dto.BookingCreateDTO;
import com.turftime.dto.BookingResponseDTO;
import com.turftime.dto.TurfSportResponseDTO;
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
	
	@GetMapping("/me")
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
	public ResponseEntity<List<BookingResponseDTO>> getAllUserBookings(){
		
		return ResponseEntity.ok(bookingService.getAllBookings());
	}
	
	

	//get all turf sports in city 
    @GetMapping("/available-sports")
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
    public List<TurfSportResponseDTO> findSportsInCity(
            @RequestParam String city,
            @RequestParam Long sportId) {
        return bookingService.findSportsInCity(city, sportId);
    }
    
    @GetMapping("/check-slot")
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
    public boolean checkSlotAvailability(
            @RequestParam Long turfSportId,
            @RequestParam String date,
            @RequestParam String startTime,
            @RequestParam String endTime) {

        return bookingService.isSlotAvailable(
                turfSportId,
                LocalDate.parse(date),
                LocalTime.parse(startTime),
                LocalTime.parse(endTime)
        );
    }
    
    @GetMapping("/available-turf-sports")
	@PreAuthorize("hasRole('PLAYER') or hasRole('ADMIN')")
    public ResponseEntity<List<AvailableSportDTO>> availableSportsInTurfs(
    		@RequestParam Long turfId,
    		@RequestParam Long sportId,
    		@RequestParam LocalTime startTime,
    		@RequestParam LocalTime endTime,
    		@RequestParam LocalDate date
    		){
    	
    	return ResponseEntity.ok(bookingService.availableSportsinTurfs(turfId, sportId, startTime, endTime, date));
    }
    

	
}

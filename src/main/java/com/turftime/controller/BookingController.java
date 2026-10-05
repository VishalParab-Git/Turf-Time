package com.turftime.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turftime.dto.AvailableSportDTO;
import com.turftime.dto.BookingCreateDTO;
import com.turftime.dto.BookingResponseDTO;
import com.turftime.dto.BookingSummaryDTO;
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
	@PreAuthorize("hasAnyRole('PLAYER','TURF_OWNER','ADMIN')")
	public ResponseEntity<BookingResponseDTO> createBooking( @Valid @RequestBody BookingCreateDTO dto) {
		
		return ResponseEntity.ok(bookingService.createBooking(dto));
	}
	
	@GetMapping("/me")
	@PreAuthorize("hasAnyRole('PLAYER','TURF_OWNER','ADMIN')")
	public ResponseEntity<List<BookingResponseDTO>> getAllUserBookings(){
		
		return ResponseEntity.ok(bookingService.getAllBookings());
	}
	
	

	//get all turf sports in city 
    @GetMapping("/available-sports")
	@PreAuthorize("hasAnyRole('PLAYER','TURF_OWNER','ADMIN')")
    public List<TurfSportResponseDTO> findSportsInCity(
            @RequestParam String city,
            @RequestParam Long sportId) {
        return bookingService.findAllTurfsBySportsAndCity(city, sportId);
    }
    
 
    
    //get available sports in turfs
    @GetMapping("/available-turf-sports")
	@PreAuthorize("hasAnyRole('PLAYER','TURF_OWNER','ADMIN')")
    public ResponseEntity<List<AvailableSportDTO>> availableSportsInTurfs(
    		@RequestParam Long turfId,
    		@RequestParam Long sportId,
    		@RequestParam LocalTime startTime,
    		@RequestParam LocalTime endTime,
    		@RequestParam LocalDate date
    		){
    	
    	return ResponseEntity.ok(bookingService.availableSportsinTurfs(turfId, sportId, startTime, endTime, date));
    }
    

    //Get all Todays bookings of turf
    //turf owner see
    @GetMapping("/today-bookings/{turfId}")
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN')")
    public ResponseEntity<BookingSummaryDTO> getTodaysBooking( @PathVariable Long turfId) {
    	
    	return ResponseEntity.ok(bookingService.getTodaysBooking(turfId));
    }
    
    //get all bookings of sport in turf by date
    //user can get all available bookings using
    //which sport he wan to play and enter date or turf id
    @GetMapping("/available/{turfId}/{sportId}")
	@PreAuthorize("hasAnyRole('TURF_OWNER','ADMIN','PLAYER')")
    public ResponseEntity<List<AvailableSportDTO>> availableSlotsForSportsInTurf(
    		@PathVariable Long turfId,
           @PathVariable Long sportId,
           @RequestParam LocalDate date){
    	
    	return ResponseEntity.ok(bookingService.availableSlotsForSportsInTurf(turfId, sportId, date));
    }
	
}

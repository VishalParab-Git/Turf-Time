package com.turftime.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.turftime.dto.AvailableSportDTO;
import com.turftime.dto.BookingCreateDTO;
import com.turftime.dto.BookingResponseDTO;
import com.turftime.dto.BookingSummaryDTO;
import com.turftime.dto.TurfSportResponseDTO;

public interface BookingService {

	BookingResponseDTO createBooking(BookingCreateDTO dto);

	List<BookingResponseDTO> getAllBookings();

	List<TurfSportResponseDTO> findAllTurfsBySportsAndCity(String city, Long sportId);

	List<AvailableSportDTO> availableSportsinTurfs(Long turfId, Long sportId, LocalTime startTime, LocalTime endTime,
			LocalDate date);

	BookingSummaryDTO getTodaysBooking(Long turfId);

	List<AvailableSportDTO> availableSlotsForSportsInTurf(Long turfId,
             Long sportId,
             LocalDate date);

}

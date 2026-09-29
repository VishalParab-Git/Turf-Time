package com.turftime.service;

import java.util.List;

import com.turftime.dto.BookingCreateDTO;
import com.turftime.dto.BookingResponseDTO;

public interface BookingService {
	
	BookingResponseDTO createBooking(BookingCreateDTO dto);

	List<BookingResponseDTO> getAllBookings();
}

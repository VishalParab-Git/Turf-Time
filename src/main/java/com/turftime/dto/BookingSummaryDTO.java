package com.turftime.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingSummaryDTO {

	private long countConfirmedBooking;
	
	private List<BookingResponseDTO> comfirmedBookings;
	
	private long countCancelledBookings;
	
	private List<BookingResponseDTO> cancelledBookings;
	
	private long countcompletedBookings;
	
	private List<BookingResponseDTO> completedBookings;
	
	
}

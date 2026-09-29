package com.turftime.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.turftime.dto.BookingCreateDTO;
import com.turftime.dto.BookingResponseDTO;
import com.turftime.entity.BookingsEntity;
import com.turftime.entity.TurfSportsEntity;
import com.turftime.entity.TurfsEntity;
import com.turftime.entity.UserEntity;
import com.turftime.enums.BookingStatus;
import com.turftime.mappers.BookingsMapper;
import com.turftime.repository.BookingRepository;
import com.turftime.repository.TurfRepository;
import com.turftime.repository.TurfSportsRepository;
import com.turftime.repository.UserRepository;
import com.turftime.service.BookingService;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final BookingsMapper bookingMapper;
	private final CodeGeneratorService codeGenerator;
	private final UserRepository userRepository;
	private final TurfRepository turfRepository;
	private final TurfSportsRepository turfSportsRepository;
	
	@Override
	public BookingResponseDTO createBooking(BookingCreateDTO dto) {

		BookingsEntity booking=bookingMapper.toEntity(dto);
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		final String username=authentication.getName();
		
		 UserEntity user=userRepository.findByUsername(username)
				 .orElseThrow(()-> new RuntimeException("User not found "));
		
		 TurfsEntity turf=turfRepository.findById(dto.getTurfId())
				 .orElseThrow(()-> new RuntimeException("Turf not found"));
		 
		 TurfSportsEntity turfSportsEntity=turfSportsRepository.findById(dto.getSportId())
				 .orElseThrow(()-> new RuntimeException("Turf Sport not found"));
		 
		 booking.setBookingNumber(codeGenerator.generateBookingCode());
		 booking.setUser(user);
		 booking.setTurf(turf);
		 booking.setSport(turfSportsEntity);
		 booking.setStatus(BookingStatus.CONFIRMED);
		 
		 bookingRepository.save(booking);
		
		return bookingMapper.toDTO(booking);
	}
	
	
	@Override
	public List<BookingResponseDTO> getAllBookings() {

		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		
		String username=authentication.getName();
		
		List<BookingsEntity> entity=bookingRepository.findByUserUsername(username)
				.orElseThrow(()-> new RuntimeException("user not found"));
				
				
		return bookingMapper.toGetUserAllBookings(entity);
	}
}

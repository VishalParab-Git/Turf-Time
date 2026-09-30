package com.turftime.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.turftime.dto.AvailableSportDTO;
import com.turftime.dto.BookingCreateDTO;
import com.turftime.dto.BookingResponseDTO;
import com.turftime.dto.TurfSportResponseDTO;
import com.turftime.entity.BookingsEntity;
import com.turftime.entity.ResourceSportEntity;
import com.turftime.entity.TurfResourceEntity;
import com.turftime.entity.TurfSportsEntity;
import com.turftime.entity.TurfsEntity;
import com.turftime.entity.UserEntity;
import com.turftime.enums.BookingStatus;
import com.turftime.enums.ResourceType;
import com.turftime.mappers.BookingsMapper;
import com.turftime.mappers.TurfSportsMapper;
import com.turftime.repository.BookingRepository;
import com.turftime.repository.ResourceSportRepository;
import com.turftime.repository.TurfRepository;
import com.turftime.repository.TurfResourceRepository;
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
	private final ResourceSportRepository resourceSportRepository;
	private final TurfSportsMapper turfSportMapper;
	private final TurfResourceRepository turfResourceRepository;

	@Override
	public BookingResponseDTO createBooking(BookingCreateDTO dto) {

		BookingsEntity booking = bookingMapper.toEntity(dto);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final String username = authentication.getName();

		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found "));

		TurfsEntity turf = turfRepository.findById(dto.getTurfId())
				.orElseThrow(() -> new RuntimeException("Turf not found"));

		TurfSportsEntity turfSportsEntity = turfSportsRepository.findById(dto.getSportId())
				.orElseThrow(() -> new RuntimeException("Turf Sport not found"));

		TurfResourceEntity turfResource=turfResourceRepository.findById(dto.getTurfResourceId())
				.orElseThrow(() -> new RuntimeException("Turf Resource not found"));
		
		booking.setBookingNumber(codeGenerator.generateBookingCode());
		booking.setUser(user);
		booking.setTurf(turf);
		booking.setTurfResource(turfResource);
		booking.setSport(turfSportsEntity);
		booking.setStatus(BookingStatus.CONFIRMED);

		bookingRepository.save(booking);

		return bookingMapper.toDTO(booking);
	}

	@Override
	public List<BookingResponseDTO> getAllBookings() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		List<BookingsEntity> entity = bookingRepository.findByUserUsername(username)
				.orElseThrow(() -> new RuntimeException("user not found"));

		return bookingMapper.toGetUserAllBookings(entity);
	}
	

	public boolean isSlotAvailable(Long turfSportId, LocalDate date, LocalTime startTime, LocalTime endTime) {
// Step 1: Get the resource linked to this sport
		List<ResourceSportEntity> resourceSports = resourceSportRepository.findByTurfSportId(turfSportId);

		for (ResourceSportEntity rs : resourceSports) {
			TurfResourceEntity resource = rs.getResource();
			int capacity = resource.getCapacity();

// Step 2: Find overlapping bookings
			List<BookingsEntity> overlapping = bookingRepository.findOverlappingBookings(turfSportId, date, startTime,
					endTime);

// Step 3: Compare with capacity
			if (overlapping.size() < capacity) {
				return true; // At least one resource slot is free+
			}
		}
		return false; // All resources are full
	}

	public List<TurfSportResponseDTO> findSportsInCity(String city, Long sportId) {
		List<TurfSportsEntity> turfSports= turfSportsRepository.findByTurfCityIgnoreCaseAndSportIdAndActiveTrue(city, sportId);
				/*.stream()
				.filter(ts -> ts.getTurf().getCity().equalsIgnoreCase(city))
				.filter(ts -> ts.getSport().getId().equals(sportId)).filter(TurfSportsEntity::isActive).toList();
		*/
		return turfSportMapper.getListOfAllTurfSport(turfSports);
	}
	
	
	@Override
	public List<AvailableSportDTO> availableSportsinTurfs(Long turfId, Long sportId,
	                                                      LocalTime startTime, LocalTime endTime, LocalDate date) {

	    List<ResourceSportEntity> resourceSport = resourceSportRepository
	            .findByResourceTurfIdAndTurfSportSportId(turfId, sportId);

	    // If no resources found, return empty list
	    if (resourceSport.isEmpty()) {
	        return Collections.emptyList();
	    }

	    ResourceType resourceType = resourceSport.get(0).getResource().getResourceType();
	    
	    // Find bookings overlapping this slot

	    List<BookingsEntity> booking =
	            bookingRepository
	                    .findBookingsForAvailability(
	                            turfId,
	                            resourceType,
	                            startTime,
	                            endTime,
	                            date
	                    );

	

	    // Collect booked resource IDs
	    Set<Long> bookedResourceIds = booking.stream()
	            .map(b -> b.getTurfResource().getId())
	            .collect(Collectors.toSet());
	    
	    List<AvailableSportDTO> availableSport = new ArrayList<>();

	    // Add only resources not booked
	    for (ResourceSportEntity resource : resourceSport) {
	    	
	    	Long resourceId=resource.getResource().getId();
	        if (bookedResourceIds.contains(resourceId) ){
	        	
	        	continue;
	        }
	            AvailableSportDTO dto = new AvailableSportDTO();
	            dto.setResourceName(resource.getResource().getResourceName());
	            dto.setSportName(resource.getTurfSport().getSport().getName());
	            dto.setResourceId(resource.getResource().getId());
	            dto.setTurfId(turfId);
	            dto.setTurfName(resource.getResource().getTurf().getName());
	            availableSport.add(dto);
	        
	    }

	    return availableSport;
	}

}

package com.turftime.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turftime.dto.AvailableSportDTO;
import com.turftime.dto.BookingCreateDTO;
import com.turftime.dto.BookingResponseDTO;
import com.turftime.dto.BookingSummaryDTO;
import com.turftime.dto.SlotDTO;
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
@Transactional
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
    private final TurfSlotService turfSlotService;

	// create Booking
	@Override
	public BookingResponseDTO createBooking(BookingCreateDTO dto) {

		BookingsEntity booking = bookingMapper.toEntity(dto);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final String username = authentication.getName();

		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found "));

		TurfsEntity turf = turfRepository.findById(dto.getTurfId())
				.orElseThrow(() -> new RuntimeException("Turf not found"));

		TurfSportsEntity turfSport = turfSportsRepository.findByTurfIdAndSportId(dto.getTurfId(), dto.getSportId())
				.orElseThrow(() -> new RuntimeException("Turf Sport not found"));

		TurfResourceEntity turfResource = turfResourceRepository
				.findByTurfIdAndId(dto.getTurfId(), dto.getTurfResourceId())
				.orElseThrow(() -> new RuntimeException("Turf Resource not found"));

		ResourceSportEntity resourceSport = resourceSportRepository
				.findByResourceIdAndTurfSportId(turfResource.getId(), turfSport.getId())
				.orElseThrow(() -> new RuntimeException(" Turf Sport not support this Resource"));

		if (resourceSport == null) {
			throw new RuntimeException("Turf Sport not support this Resource");
		}

		// check booking date not is past
		if (dto.getBookingDate().isBefore(LocalDate.now())) {
			throw new RuntimeException("Booking date cannot be in the past");
		}

		booking.setBookingNumber(codeGenerator.generateBookingCode());
		booking.setUser(user);
		booking.setTurf(turf);
		booking.setTurfResource(turfResource);
		booking.setSport(turfSport);
		booking.setStatus(BookingStatus.CONFIRMED);

		bookingRepository.save(booking);

		return bookingMapper.toDTO(booking);
	}

	// find all bookings of user
	@Override
	@Transactional(readOnly = true)
	public List<BookingResponseDTO> getAllBookings() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		List<BookingsEntity> entity = bookingRepository.findByUserUsername(username);

		if (entity.isEmpty()) {
			throw new RuntimeException("No Booking Found");
		}

		return bookingMapper.toListOfBookingEntityToListOfBookingResponse(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TurfSportResponseDTO> findAllTurfsBySportsAndCity(String city, Long sportId) {
		List<TurfSportsEntity> turfSports = turfSportsRepository.findByTurfCityIgnoreCaseAndSportIdAndActiveTrue(city,
				sportId);

		return turfSportMapper.getListOfAllTurfSport(turfSports);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AvailableSportDTO> availableSportsinTurfs(Long turfId, Long sportId, LocalTime startTime,
			LocalTime endTime, LocalDate date) {

		List<ResourceSportEntity> resourceSport = resourceSportRepository
				.findByResourceTurfIdAndTurfSportSportId(turfId, sportId);

		// If no resources found, return empty list
		if (resourceSport.isEmpty()) {
			return Collections.emptyList();
		}

		ResourceType resourceType = resourceSport.get(0).getResource().getResourceType();

		// Find bookings overlapping this slot

		List<BookingsEntity> booking = bookingRepository.findBookingsForAvailability(turfId, resourceType, startTime,
				endTime, date);

		// Collect booked resource IDs
		Set<Long> bookedResourceIds = booking.stream().map(b -> b.getTurfResource().getId())
				.collect(Collectors.toSet());

		List<AvailableSportDTO> availableSport = new ArrayList<>();

		// Add only resources not booked
		for (ResourceSportEntity resource : resourceSport) {

			Long resourceId = resource.getResource().getId();
			if (bookedResourceIds.contains(resourceId)) {

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

	// Turf Owner see todays all confirmed bookings
	//only turf owner see her todays bookings
	@Override
	public BookingSummaryDTO getTodaysBooking(Long turfId) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final String username = authentication.getName();

		TurfsEntity turf = turfRepository.findById(turfId).orElseThrow(() -> new RuntimeException("Turf not found"));

		if (!turf.getOwner().getUser().getUsername().equals(username)) {
			throw new RuntimeException("Please check turf");
		}

		List<BookingsEntity> confirmedBookings = bookingRepository.findByBookingDateAndTurfIdAndStatus(LocalDate.now(),
				turfId, BookingStatus.CONFIRMED);
		List<BookingsEntity> cancelledBookings = bookingRepository.findByBookingDateAndTurfIdAndStatus(LocalDate.now(),
				turfId, BookingStatus.CANCELLED);
		List<BookingsEntity> completedBookings = bookingRepository.findByBookingDateAndTurfIdAndStatus(LocalDate.now(),
				turfId, BookingStatus.COMPLETED);

		long countConfirmedBookings = confirmedBookings.size();
		long countcancelledBookings = cancelledBookings.size();
		long countcompletedBookings = completedBookings.size();

		List<BookingResponseDTO> confirmedBookingsResponse = bookingMapper
				.toListOfBookingEntityToListOfBookingResponse(confirmedBookings);
		List<BookingResponseDTO> cancelledBookingsResponse = bookingMapper
				.toListOfBookingEntityToListOfBookingResponse(cancelledBookings);
		List<BookingResponseDTO> completedBookingsResponse = bookingMapper
				.toListOfBookingEntityToListOfBookingResponse(completedBookings);

		BookingSummaryDTO bookingSummary = new BookingSummaryDTO();

		bookingSummary.setCountCancelledBookings(countcancelledBookings);
		bookingSummary.setCountcompletedBookings(countcompletedBookings);
		bookingSummary.setCountConfirmedBooking(countConfirmedBookings);
		bookingSummary.setCancelledBookings(cancelledBookingsResponse);
		bookingSummary.setComfirmedBookings(confirmedBookingsResponse);
		bookingSummary.setCompletedBookings(completedBookingsResponse);

		return bookingSummary;
	}

	@Override
	@Transactional(readOnly = true)
	public List<AvailableSportDTO> availableSlotsForSportsInTurf(Long turfId, Long sportId, LocalDate date) {

		// 1. Validate date (future only)
		if (!date.isAfter(LocalDate.now())) {
			throw new RuntimeException("plese check date!! ");
		}

		// 2. Find all resources in turf that support this sport
		List<ResourceSportEntity> resourceSport = resourceSportRepository
				.findByResourceTurfIdAndTurfSportSportId(turfId, sportId);

		if (resourceSport.isEmpty()) {
			return Collections.emptyList();
		}

		List<Long> resourceIds = resourceSport.stream().map(r -> r.getResource().getId()).collect(Collectors.toList());

		// 3. Fetch confirmed bookings for those resources on this date
		List<BookingsEntity> bookings = bookingRepository.findByTurfIdAndResourceIdsAndBookingDateAndStatus(turfId,
				resourceIds, date, BookingStatus.CONFIRMED);

		// Collect booked resource IDs for quick lookup
		Map<Long, List<BookingsEntity>> bookingsByResource = bookings.stream()
				.collect(Collectors.groupingBy(b -> b.getTurfResource().getId()));

		// 4. Generate slots based on turf open/close times
		TurfsEntity turf = resourceSport.get(0).getResource().getTurf();
		LocalTime openingTime = turf.getOpeningTime();
		LocalTime closingTime = turf.getClosingTime();

		List<AvailableSportDTO> availableSport = new ArrayList<>();

		// 5. For each resource, filter slots
		for (Long resourceId : resourceIds) {

			// Generate fresh slot list for this resource
			List<SlotDTO> allSlots = turfSlotService.generateSlots(openingTime, closingTime, 60);

			// Get bookings for this resource
			List<BookingsEntity> resourceBookings = bookingsByResource.getOrDefault(resourceId,
					Collections.emptyList());

			// Filter slots
			List<SlotDTO> availableSlots = allSlots.stream().filter(slot -> {

				if (date.equals(LocalDate.now())) {
					// Remove past slots
					if (slot.getStartTime().isBefore(LocalTime.now())) {
						return false;
					}
				}
				// Remove slots overlapping with CONFIRMED bookings
				boolean isBooked = resourceBookings.stream().anyMatch(b -> b.getStatus() == BookingStatus.CONFIRMED
						&& b.getStartTime().isBefore(slot.getEndTime()) && b.getEndTime().isAfter(slot.getStartTime()));
				return !isBooked;
			}).collect(Collectors.toList());

			// Map resource + its available slots into DTO
			ResourceSportEntity resource = resourceSport.stream()
					.filter(r -> r.getResource().getId().equals(resourceId)).findFirst().orElseThrow();

			AvailableSportDTO dto = new AvailableSportDTO();
			dto.setResourceId(resourceId);
			dto.setResourceName(resource.getResource().getResourceName());
			dto.setSportName(resource.getTurfSport().getSport().getName());
			dto.setTurfId(turfId);
			dto.setTurfName(resource.getResource().getTurf().getName());
			dto.setSlotsAvailable(availableSlots); // ✅ attach all slots here
			dto.setTurfOpean(openingTime);
			dto.setTurfClosed(closingTime);
			availableSport.add(dto);
		}

		return availableSport;
	}

}

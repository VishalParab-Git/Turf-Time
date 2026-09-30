package com.turftime.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.turftime.entity.BookingsEntity;
import com.turftime.enums.ResourceType;

@Repository
public interface BookingRepository extends JpaRepository<BookingsEntity, Long> {
	
	Optional<List<BookingsEntity>> findByUserUsername(String username);
	
	
	 @Query("SELECT b FROM BookingsEntity b " +
	           "WHERE b.sport.id = :sportId " +
	           "AND b.bookingDate = :date " +
	           "AND (b.startTime < :endTime AND b.endTime > :startTime) " +
	           "AND b.status = com.turftime.enums.BookingStatus.CONFIRMED")
	    List<BookingsEntity> findOverlappingBookings(Long sportId, LocalDate date,
	                                                 LocalTime startTime, LocalTime endTime);
	 
	 @Query("""
		        SELECT b
		        FROM BookingsEntity b
		        WHERE b.turf.id = :turfId
		          AND b.turfResource.resourceType = :resourceType
		          AND b.bookingDate = :date
		          AND b.startTime < :endTime
		          AND b.endTime > :startTime
		        """)
		List<BookingsEntity> findBookingsForAvailability(
		        @Param("turfId") Long turfId,
		        @Param("resourceType") ResourceType resourceType,
		        @Param("startTime") LocalTime startTime,
		        @Param("endTime") LocalTime endTime,
		        @Param("date") LocalDate date
		);	

	 
}

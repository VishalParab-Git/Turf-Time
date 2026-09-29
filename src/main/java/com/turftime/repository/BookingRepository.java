package com.turftime.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turftime.entity.BookingsEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingsEntity, Long> {
	
	Optional<List<BookingsEntity>> findByUserUsername(String username);

}

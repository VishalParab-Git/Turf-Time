package com.turftime.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.turftime.dto.BookingCreateDTO;
import com.turftime.dto.BookingResponseDTO;
import com.turftime.entity.BookingsEntity;

@Mapper(componentModel="spring")
public interface BookingsMapper {

	@Mapping(target="id",ignore=true)
	@Mapping(target="bookingNumber",ignore=true)
	@Mapping(target="user",ignore=true)
	@Mapping(target="turf",ignore=true)
	@Mapping(target="sport",ignore=true)
	@Mapping(target="status",ignore=true)
	@Mapping(target="createdAt",ignore=true)
	BookingsEntity toEntity(BookingCreateDTO dto);
	
	@Mapping(target="userName",ignore=true)
	@Mapping(target="turfName",source="turf.name")
	@Mapping(target="sportName",source="sport.sport.name")
	BookingResponseDTO toDTO(BookingsEntity entity);
	
	@Mapping(target="userName",ignore=true)
	@Mapping(target="turfName",source="turf.name")
	@Mapping(target="sportName",source="sport.name")
	List<BookingResponseDTO> toGetUserAllBookings(List<BookingsEntity> entity);
}

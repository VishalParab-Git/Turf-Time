package com.turftime.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.turftime.dto.TurfSportCreateDTO;
import com.turftime.dto.TurfSportResponseDTO;
import com.turftime.entity.TurfSportsEntity;

@Mapper(componentModel="spring")
public interface TurfSportsMapper {

	@Mapping(target="id",ignore=true)
	@Mapping(target="turf",ignore=true)
	@Mapping(target="sport",ignore=true)
	@Mapping(target="createdAt",ignore=true)
	@Mapping(target="updatedAt",ignore=true)
	TurfSportsEntity toEntity(TurfSportCreateDTO dto);
	
	@Mapping(target="turfId",source="turf.id")
	@Mapping(target="sportId",source="sport.id")
	TurfSportResponseDTO toDTO(TurfSportsEntity entity);
	
	@Mapping(target="turfId",source="turf.id")
	@Mapping(target="sportId",source="sport.id")
	List<TurfSportResponseDTO> getListOfAllTurfSport(List<TurfSportsEntity> entity);
	
}

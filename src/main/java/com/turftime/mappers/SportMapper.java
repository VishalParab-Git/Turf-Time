package com.turftime.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.turftime.dto.SportCreateDTO;
import com.turftime.dto.SportResponseDTO;
import com.turftime.entity.SportsEntity;

@Mapper(componentModel="spring")
public interface SportMapper {

	@Mapping(target="id",ignore=true)
	@Mapping(target="createdAt",ignore=true)
	@Mapping(target="updatedAt",ignore=true)
	SportsEntity toEntity(SportCreateDTO dto);
	
	SportResponseDTO toDTO(SportsEntity entity);
	
	List<SportResponseDTO> toListOfSports(List<SportsEntity> entity);
}

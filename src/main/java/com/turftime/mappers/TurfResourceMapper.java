package com.turftime.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.turftime.dto.TurfResourceCreateDTO;
import com.turftime.dto.TurfResourceResponseDTO;
import com.turftime.entity.TurfResourceEntity;

@Mapper(componentModel="spring")
public interface TurfResourceMapper {

	@Mapping(target="id",ignore=true)
	@Mapping(target="turf",ignore=true)
	@Mapping(target="status",ignore=true)
	@Mapping(target="createdAt",ignore=true)
	@Mapping(target="updatedAt",ignore=true)
	TurfResourceEntity toEntity(TurfResourceCreateDTO dto);
	
	@Mapping(source="turf.id" , target="turfId")
	TurfResourceResponseDTO toDTO(TurfResourceEntity entity);
	
	@Mapping(source="turf.id" , target="turfId")
	List<TurfResourceResponseDTO> toListOfTurfResourceToResponseDTO(List<TurfResourceEntity> entity);
}

package com.turftime.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.turftime.dto.TurfCreateDTO;
import com.turftime.dto.TurfResponseDTO;
import com.turftime.entity.TurfsEntity;

@Mapper(componentModel="spring")
public interface TurfMapper {

	@Mapping(target="id",ignore=true)
	@Mapping(target="owner",ignore=true)
	@Mapping(target="status",ignore=true)
	@Mapping(target="createdAt",ignore=true)
	@Mapping(target="updatedAt",ignore=true)
	TurfsEntity toEntity(TurfCreateDTO dto);
	
	@Mapping(target="ownerId",source="owner.id")
	TurfResponseDTO toDTO(TurfsEntity entity); 
}

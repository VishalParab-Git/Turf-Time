package com.turftime.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.turftime.dto.TurfCreateDTO;
import com.turftime.dto.TurfResponseDTO;
import com.turftime.dto.TurfUpdateDTO;
import com.turftime.entity.TurfsEntity;

@Mapper(componentModel = "spring")
public interface TurfMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "owner", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	TurfsEntity toEntity(TurfCreateDTO dto);

	@Mapping(target = "ownerId", source = "owner.id")
	TurfResponseDTO toDTO(TurfsEntity entity);

	@Mapping(target = "ownerId", source = "owner.id")
	List<TurfResponseDTO> toListOfTurfRespnseDTO(List<TurfsEntity> entity);

	@Mapping(target = "id", ignore = true) // prevent overwriting
	@Mapping(target = "owner", ignore = true) // prevent overwriting
	@Mapping(target = "status", ignore = true) // prevent overwriting
	@Mapping(target = "createdAt", ignore = true) // prevent overwriting
	@Mapping(target = "updatedAt", ignore = true) // prevent overwriting
	void updateTurfDTOtoEntity(TurfUpdateDTO dto, @MappingTarget TurfsEntity entity);
}

package com.turftime.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.turftime.dto.TurfOwnerCreateDTO;
import com.turftime.dto.TurfOwnerProfileUpdateDTO;
import com.turftime.dto.TurfOwnerResponseDTO;
import com.turftime.entity.TurfOwnersEntity;

@Mapper(componentModel="spring")
public interface TurfOwnersMapper {

	@Mapping(target="id",ignore=true)
	@Mapping(target="user",ignore=true)
	@Mapping(target="createdAt", ignore=true)
	@Mapping(target="updatedAt", ignore=true)
	@Mapping(target="turfs", ignore=true) 
	@Mapping(target="status",ignore=true)
	TurfOwnersEntity toEntity(TurfOwnerCreateDTO dto);
	
	@Mapping(target="userId",source="user.id")
	TurfOwnerResponseDTO toDTO(TurfOwnersEntity entity);
	


	void updateTurfOwnerProfileToEntity(TurfOwnerProfileUpdateDTO dto, @MappingTarget TurfOwnersEntity entity);
}

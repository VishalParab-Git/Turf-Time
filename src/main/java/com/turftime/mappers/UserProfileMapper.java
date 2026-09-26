package com.turftime.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.turftime.dto.UserProfileCreateDTO;
import com.turftime.dto.UserProfileResponseDTO;
import com.turftime.entity.UserProfileEntity;

@Mapper(componentModel="Spring")
public interface UserProfileMapper {

	@Mapping(target="id", ignore=true)
	@Mapping(target="user", ignore=true)
	@Mapping(target="createdAt", ignore=true)
	@Mapping(target="updatedAt", ignore=true)
	UserProfileEntity toEntity(UserProfileCreateDTO dto);
	
	@Mapping(target="userId", source="user.id")
	UserProfileResponseDTO toDTO(UserProfileEntity entity);
}

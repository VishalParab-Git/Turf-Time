package com.turftime.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.turftime.dto.UserRegisterDTO;
import com.turftime.dto.UserResponseDTO;
import com.turftime.entity.UserEntity;

@Mapper(componentModel="spring")
public interface UserMapper {

	@Mapping(target="id",ignore=true)
	@Mapping(target="username",source="email")
	@Mapping(target="role",ignore=true)
	@Mapping(target="status",ignore=true)
	@Mapping(target="createdAt",ignore=true)
	@Mapping(target="updatedAt",ignore=true)
	UserEntity toEntity(UserRegisterDTO dto);
	
	@Mapping(target="email",source="username")
	UserResponseDTO toDTO(UserEntity entity);
	
}

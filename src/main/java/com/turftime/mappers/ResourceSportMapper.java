package com.turftime.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.turftime.dto.ResourceSportCreateDTO;
import com.turftime.dto.ResourceSportResponseDTO;
import com.turftime.entity.ResourceSportEntity;

@Mapper(componentModel="spring")
public interface ResourceSportMapper {

	@Mapping(target="id",ignore=true)
	@Mapping(target="resource",ignore=true)
	@Mapping(target="turfSport",ignore=true)
	@Mapping(target="createdAt",ignore=true)
	ResourceSportEntity toEntity(ResourceSportCreateDTO dto);
	
	@Mapping(source="resource.id",target="resourceId")
	@Mapping(source="resource.resourceName",target="resourceName")
	@Mapping(source="turfSport.sport.name",target="turfSportName")
	ResourceSportResponseDTO toDTO(ResourceSportEntity entity);
	
	@Mapping(source="resource.id",target="resource")
	@Mapping(source="resource.resourceName",target="resourceName")
	@Mapping(source="turfSport.sport.name",target="turfSportName")
	List<ResourceSportResponseDTO> toListOfResourcesportDTO(List<ResourceSportEntity> entity);
}

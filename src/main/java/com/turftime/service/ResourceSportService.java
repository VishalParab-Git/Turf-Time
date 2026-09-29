package com.turftime.service;

import java.util.List;

import com.turftime.dto.ResourceSportCreateDTO;
import com.turftime.dto.ResourceSportResponseDTO;

public interface ResourceSportService {

	ResourceSportResponseDTO createResourceSport(ResourceSportCreateDTO dto);
	
	List<ResourceSportResponseDTO>	getResourceSportById(Long turfId,Long resourceId);
	
}

package com.turftime.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turftime.dto.ResourceSportCreateDTO;
import com.turftime.dto.ResourceSportResponseDTO;
import com.turftime.entity.ResourceSportEntity;
import com.turftime.entity.TurfResourceEntity;
import com.turftime.entity.TurfSportsEntity;
import com.turftime.mappers.ResourceSportMapper;
import com.turftime.repository.ResourceSportRepository;
import com.turftime.repository.TurfResourceRepository;
import com.turftime.repository.TurfSportsRepository;
import com.turftime.service.ResourceSportService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResourceSportServiceImpl implements ResourceSportService {

	private final ResourceSportRepository resourceSportRepository;
	private final ResourceSportMapper resourceSportMapper;
	private final TurfResourceRepository turfResourceRepository;
	private final TurfSportsRepository turfSportRepository;
	
	@Override
	public ResourceSportResponseDTO createResourceSport(ResourceSportCreateDTO dto) {
		ResourceSportEntity resourceSport=resourceSportMapper.toEntity(dto);
		
		TurfResourceEntity turfResource=turfResourceRepository.findById(dto.getResourceId())
				.orElseThrow(()->new RuntimeException("Turf Resource not found"));
		
		TurfSportsEntity turfSport=turfSportRepository.findById(dto.getTurfSportId())
				.orElseThrow(()-> new RuntimeException(" Turf sport not found"));
		
		if(!turfResource.getTurf().getId().equals(turfSport.getTurf().getId())) {
			
			throw new RuntimeException("Turf Resource and Sport not in same Turf");
		}
	
		resourceSport.setResource(turfResource);
		resourceSport.setTurfSport(turfSport);
		resourceSportRepository.save(resourceSport);
		
		return resourceSportMapper.toDTO(resourceSport);
	}
	
	@Override
	public List<ResourceSportResponseDTO> getResourceSportById(Long turfId, Long resourceId) {
		
		List<ResourceSportEntity> resourceSports=resourceSportRepository.findByResourceTurfIdAndResourceId(turfId, resourceId);
		
		return resourceSportMapper.toListOfResourcesportDTO(resourceSports);
	}
	
}

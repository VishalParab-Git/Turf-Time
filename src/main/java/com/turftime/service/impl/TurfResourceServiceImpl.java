package com.turftime.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.turftime.dto.TurfResourceCreateDTO;
import com.turftime.dto.TurfResourceResponseDTO;
import com.turftime.dto.TurfResourceUpdateDTO;
import com.turftime.entity.TurfOwnersEntity;
import com.turftime.entity.TurfResourceEntity;
import com.turftime.entity.TurfsEntity;
import com.turftime.enums.ResourceStatus;
import com.turftime.mappers.TurfResourceMapper;
import com.turftime.repository.TurfOwnerRepository;
import com.turftime.repository.TurfRepository;
import com.turftime.repository.TurfResourceRepository;
import com.turftime.service.TurfResourceService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TurfResourceServiceImpl  implements TurfResourceService{

	private final TurfResourceRepository turfResourceRepository;
	private final TurfResourceMapper turfResourceMapper;
	private final TurfRepository turfRepository;
	private final TurfOwnerRepository turfOwnerRepository;
	
	
	//Create Turf Resource
	@Override
	public TurfResourceResponseDTO createTurfResource(TurfResourceCreateDTO dto) {
		
		TurfResourceEntity turfResource=turfResourceMapper.toEntity(dto);
		
		TurfsEntity turf=turfRepository.findById(dto.getTurfId())
				.orElseThrow(()-> new RuntimeException("turf not found"));
		
		TurfOwnersEntity owner=turfOwnerRepository.findById(turf.getOwner().getId())
				.orElseThrow(()-> new RuntimeException("Turf Owner Not found"));
		
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		final String username=authentication.getName();
		
		//check current user is real turf owner of turf
		if(!owner.getUser().getUsername().equals(username)) {
			
			throw new RuntimeException("Turf Owner not found");
		}
		
		turfResource.setTurf(turf);
		turfResource.setStatus(ResourceStatus.ACTIVE);
		
		turfResourceRepository.save(turfResource);
		
		return turfResourceMapper.toDTO(turfResource);
	}
	
	
	//Get Turf Resouce By Id
	@Override
	public TurfResourceResponseDTO getTurfResourceById(Long turfId,Long turfResourceId) {
		
		TurfsEntity turf=turfRepository.findById(turfId)
				.orElseThrow(()-> new RuntimeException("turf not found"));
				
		TurfOwnersEntity owner=turfOwnerRepository.findById(turf.getOwner().getId())
				.orElseThrow(()-> new RuntimeException("Turf Owner Not found"));
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		final String username=authentication.getName();
		
		//check current user is real turf owner of turf
		if(!owner.getUser().getUsername().equals(username)) {
			
			throw new RuntimeException("Turf Owner not found");
		}
		
		
		TurfResourceEntity turfResource=turfResourceRepository.findByTurfIdAndId(turfId, turfResourceId)
								.orElseThrow(()-> new RuntimeException("Turf Resource Not found"));

		
		return turfResourceMapper.toDTO(turfResource);
	}


	//Get All Turf Resource By Turf Id
	//also player can see all resources in turf
	@Override
	public List<TurfResourceResponseDTO> getAllTurfResource(Long turfId) {
		
	
		List<TurfResourceEntity> turfResources=turfResourceRepository.findAllByTurfIdAndStatusNot(turfId,ResourceStatus.INACTIVE);
		
		if(turfResources.isEmpty()) {
			throw new RuntimeException("Turf Resources not found ");
		}
		
		return turfResourceMapper.toListOfTurfResourceToResponseDTO(turfResources);
	}


	//Update Turf Resource By Turf Id or Turf Resource
	@Override
	public TurfResourceResponseDTO updateTurfResource(Long turfId, Long resourceId, TurfResourceUpdateDTO dto) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		TurfResourceEntity resource=turfResourceRepository.findByTurfIdAndId(turfId, resourceId)
				.orElseThrow(()-> new RuntimeException("Turf Resource Not found"));


		if (resource == null) {
		    throw new RuntimeException("Resource not found for turfId=" + turfId + " and resourceId=" + resourceId);
		}
		
		if(!resource.getTurf().getOwner().getUser().getUsername().equals(username)) {
			throw new RuntimeException("User not Owner of this Turf");
		}
		
		turfResourceMapper.updateTurfResourceToEntity(dto, resource);
		
		turfResourceRepository.save(resource);
		
		return turfResourceMapper.toDTO(resource);
	}


	//Delete Turf Resource By Turf Id and Resource Id
	@Transactional
	@Override
	public String deleteTurfResource(Long turfId, Long resourceId) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		TurfResourceEntity resource=turfResourceRepository.findByTurfIdAndId(turfId, resourceId)
				.orElseThrow(()-> new RuntimeException("Turf Resource Not found"));


		if (resource == null) {
		    throw new RuntimeException("Resource not found for turfId=" + turfId + " and resourceId=" + resourceId);
		}
		
		if(!resource.getTurf().getOwner().getUser().getUsername().equals(username)) {
			throw new RuntimeException("User not Owner of this Turf");
		}
		
		resource.setStatus(ResourceStatus.INACTIVE);
		
		turfResourceRepository.save(resource);
		
		return "Turf Resource deleted Successfully!!!!!";
	}
	
	
	
}

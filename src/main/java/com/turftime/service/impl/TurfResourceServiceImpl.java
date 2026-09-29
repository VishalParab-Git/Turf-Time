package com.turftime.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.turftime.dto.TurfResourceCreateDTO;
import com.turftime.dto.TurfResourceResponseDTO;
import com.turftime.entity.TurfOwnersEntity;
import com.turftime.entity.TurfResourceEntity;
import com.turftime.entity.TurfsEntity;
import com.turftime.enums.ResourceStatus;
import com.turftime.mappers.TurfResourceMapper;
import com.turftime.repository.TurfOwnerRepository;
import com.turftime.repository.TurfRepository;
import com.turftime.repository.TurfResourceRepository;
import com.turftime.service.TurfResourceService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TurfResourceServiceImpl  implements TurfResourceService{

	private final TurfResourceRepository turfResourceRepository;
	private final TurfResourceMapper turfResourceMapper;
	private final TurfRepository turfRepository;
	private final TurfOwnerRepository turfOwnerRepository;
	
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
		
		
		TurfResourceEntity turfResource=turfResourceRepository.findByTurfIdAndId(turfId, turfResourceId);
				
		
		return turfResourceMapper.toDTO(turfResource);
	}


	@Override
	public List<TurfResourceResponseDTO> getAllTurfResource(Long turfId) {
	
		List<TurfResourceEntity> turfResources=turfResourceRepository.findAllByTurfId(turfId)
				.orElseThrow(()-> new RuntimeException("Turfs Resource not available"));
		
		return turfResourceMapper.toListOfTurfResourceToResponseDTO(turfResources);
	}
	
	
	
}

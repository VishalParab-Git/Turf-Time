package com.turftime.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.turftime.dto.TurfCreateDTO;
import com.turftime.dto.TurfResponseDTO;
import com.turftime.entity.TurfOwnersEntity;
import com.turftime.entity.TurfsEntity;
import com.turftime.enums.TurfStatus;
import com.turftime.mappers.TurfMapper;
import com.turftime.repository.TurfOwnerRepository;
import com.turftime.repository.TurfRepository;
import com.turftime.service.TurfsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TurfsServiceImpl implements TurfsService {

	private final TurfRepository turfRepository;
	private final TurfOwnerRepository ownerRepository;
	private final TurfMapper turfMapper;
	
	@Override
	public TurfResponseDTO createTurf(TurfCreateDTO dto) {

		TurfsEntity turf=turfMapper.toEntity(dto);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		TurfOwnersEntity owner= ownerRepository.findByUserUsername(username)
				.orElseThrow(()->new RuntimeException("Owner not found"));
		
		turf.setOwner(owner);
		turf.setStatus(TurfStatus.AVAILABLE);
		
		turfRepository.save(turf);
		return turfMapper.toDTO(turf);
	}
	
	@Override
	public TurfResponseDTO getTurf() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		TurfsEntity turf=turfRepository.findByOwnerUserUsername(username)
				.orElseThrow(()-> new RuntimeException("Turf Owner not found"));
		
		return turfMapper.toDTO(turf);
	}

	@Override
	public List<TurfResponseDTO> findAllTurfsByCityorState(String city, String state) {
		
		List<TurfsEntity> turfs=turfRepository.findAllByCityAndState(city, state);
		
		return turfMapper.toListOfTurfRespnseDTO(turfs);
	}
	
	
}

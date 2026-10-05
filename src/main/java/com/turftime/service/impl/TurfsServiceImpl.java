package com.turftime.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turftime.dto.TurfCreateDTO;
import com.turftime.dto.TurfResponseDTO;
import com.turftime.dto.TurfUpdateDTO;
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
@Transactional
public class TurfsServiceImpl implements TurfsService {

	private final TurfRepository turfRepository;
	private final TurfOwnerRepository ownerRepository;
	private final TurfMapper turfMapper;
	
	//Create Turf
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
	
	//Get My All Turfs
	@Transactional(readOnly=true)
	@Override
	public List<TurfResponseDTO> getMyTurf() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		List<TurfsEntity> turf=turfRepository.findByOwnerUserUsernameAndStatusNot(username,TurfStatus.CLOSED);
				
		if(turf.isEmpty()) {
				throw new RuntimeException("Turf Owner not found");
		}
		return turfMapper.toListOfTurfRespnseDTO(turf);
	}

	//Find All Available Turfs By City or State
	@Transactional(readOnly=true)
	@Override
	public List<TurfResponseDTO> findAllTurfsByCityorState(String city, String state) {
		
		List<TurfsEntity> turfs=turfRepository.findAllByCityIgnoreCaseAndStateIgnoreCaseAndStatus(city, state, TurfStatus.AVAILABLE);
		
		return turfMapper.toListOfTurfRespnseDTO(turfs);
	}

	//Update Turf Profile
	@Override
	public TurfResponseDTO updateTurf(Long turfId, TurfUpdateDTO dto) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		TurfsEntity turf=turfRepository.findById(turfId)
				.orElseThrow(()-> new RuntimeException("Turf  not found"));

		if(!turf.getOwner().getUser().getUsername().equals(username)) {
			throw new RuntimeException("Please check Turf Id");
		}
		
		turfMapper.updateTurfDTOtoEntity(dto, turf);
		
		turfRepository.save(turf);
		
		return turfMapper.toDTO(turf);
	}

	//Delete Turf Account
	@Override
	public void deleteTurfAccount(Long turfId) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		TurfsEntity turf=turfRepository.findById(turfId)
				.orElseThrow(()-> new RuntimeException("Turf  not found"));

		if(!turf.getOwner().getUser().getUsername().equals(username)) {
			throw new RuntimeException("User not Owner of this Turf");
		}
		
		turf.setStatus(TurfStatus.CLOSED);
		turfRepository.save(turf);
	}
	
	
}

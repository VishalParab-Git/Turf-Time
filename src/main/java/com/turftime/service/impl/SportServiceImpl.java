package com.turftime.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turftime.dto.SportCreateDTO;
import com.turftime.dto.SportResponseDTO;
import com.turftime.entity.SportsEntity;
import com.turftime.mappers.SportMapper;
import com.turftime.repository.SportRepository;
import com.turftime.service.SportsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SportServiceImpl implements SportsService {

	private final SportRepository sportRepository;
	private final SportMapper sportMapper;
	
	@Override
	public SportResponseDTO createSport(SportCreateDTO dto) {
		
		SportsEntity sport=sportMapper.toEntity(dto);
		sportRepository.save(sport);
		return sportMapper.toDTO(sport);
	}
	
	
	@Override
	public List<SportResponseDTO> getAllSport() {

		List<SportsEntity> sports=sportRepository.findAll();
		return sportMapper.toListOfSports(sports);
		
	}
	
	
}

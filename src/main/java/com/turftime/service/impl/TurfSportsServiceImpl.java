package com.turftime.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turftime.dto.TurfSportCreateDTO;
import com.turftime.dto.TurfSportResponseDTO;
import com.turftime.entity.SportsEntity;
import com.turftime.entity.TurfSportsEntity;
import com.turftime.entity.TurfsEntity;
import com.turftime.mappers.TurfSportsMapper;
import com.turftime.repository.SportRepository;
import com.turftime.repository.TurfRepository;
import com.turftime.repository.TurfSportsRepository;
import com.turftime.service.TurfSportsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TurfSportsServiceImpl implements TurfSportsService {

	private final TurfSportsRepository turfSportRepository;
	private final TurfSportsMapper turfSportsMapper;
	private final TurfRepository turfRepository;
	private final SportRepository sportRepository;
	
	@Override
	public TurfSportResponseDTO createTurfSports(TurfSportCreateDTO dto) {

		TurfSportsEntity turfSport=turfSportsMapper.toEntity(dto);
		
		TurfsEntity turf=turfRepository.findById(dto.getTurfId())
				.orElseThrow(()-> new RuntimeException("Turf not found"));
		//
		
		SportsEntity sport=sportRepository.findById(dto.getSportId())
				.orElseThrow(()-> new RuntimeException("Sport not found"));
		
		
		turfSport.setTurf(turf);
		turfSport.setSport(sport);
		turfSportRepository.save(turfSport);
		
		
		return turfSportsMapper.toDTO(turfSport);
	}
	
	
	@Override
	public List<TurfSportResponseDTO> getAllTurfSport(Long id) {
		
		List<TurfSportsEntity> turfSports=turfSportRepository.findAllByTurfId(id)
				.orElseThrow(()-> new RuntimeException("Turf sports not found"));
		return turfSportsMapper.getListOfAllTurfSport(turfSports);
	}
	
	
	
}

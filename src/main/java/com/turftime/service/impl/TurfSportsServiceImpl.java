package com.turftime.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.turftime.dto.TurfSportCreateDTO;
import com.turftime.dto.TurfSportResponseDTO;
import com.turftime.dto.TurfSportUpdateDTO;
import com.turftime.entity.SportsEntity;
import com.turftime.entity.TurfSportsEntity;
import com.turftime.entity.TurfsEntity;
import com.turftime.mappers.TurfSportsMapper;
import com.turftime.repository.SportRepository;
import com.turftime.repository.TurfRepository;
import com.turftime.repository.TurfSportsRepository;
import com.turftime.service.TurfSportsService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
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
		
		turfSport.setActive(true);
		turfSport.setTurf(turf);
		turfSport.setSport(sport);
		turfSportRepository.save(turfSport);
		
		
		return turfSportsMapper.toDTO(turfSport);
	}
	
	
	@Override
	public List<TurfSportResponseDTO> getAllTurfSport(Long id) {
		
		List<TurfSportsEntity> turfSports=turfSportRepository.findAllByTurfId(id);
		
		if(turfSports.isEmpty()) {
			throw new RuntimeException("NO Sports Found in Turf ");
		}
		return turfSportsMapper.getListOfAllTurfSport(turfSports);
	}


	@Override
	public TurfSportResponseDTO updateTurfSportPrice(TurfSportUpdateDTO dto) {
		
		TurfSportsEntity turfSport=turfSportRepository.findByIdAndTurfId(dto.getTurfSportId(), dto.getTurfId());
		
		if(turfSport==null) {
			throw new RuntimeException("Turf Id or Turf Sport Id not found");
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		if(!username.equals(turfSport.getTurf().getOwner().getUser().getUsername())) {
			throw new RuntimeException("User not allow to access this Turf");
		}
		
		turfSportsMapper.updateTurfSportPrice(dto, turfSport);
		
		turfSportRepository.save(turfSport);
		
		return turfSportsMapper.toDTO(turfSport);
	}


	@Override
	public void deleteTurfSport(Long turfId, Long turfSportId) {
		
		TurfSportsEntity turfSport=turfSportRepository.findByIdAndTurfId( turfSportId, turfId);
		
		if(turfSport==null) {
			throw new RuntimeException("Turf Id or Turf Sport Id not found");
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		if(!username.equals(turfSport.getTurf().getOwner().getUser().getUsername())) {
			throw new RuntimeException("User not allow to access this Turf");
		}
		
		turfSport.setActive(false);
		
		turfSportRepository.save(turfSport);
		
	}
	
	@Transactional
	@Override
	public String deleteAllTurfSports(Long turfId) {
		
		turfSportRepository.deleteAllByTurfId(turfId);
		
		return " All Turf Sports Deleted Successfully!!!!";
	}
	
}

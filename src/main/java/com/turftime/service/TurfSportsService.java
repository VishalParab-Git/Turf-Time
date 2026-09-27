package com.turftime.service;

import java.util.List;

import com.turftime.dto.TurfSportCreateDTO;
import com.turftime.dto.TurfSportResponseDTO;

public interface TurfSportsService {

	
	TurfSportResponseDTO createTurfSports(TurfSportCreateDTO dto);
	
	List<TurfSportResponseDTO> getAllTurfSport(Long id);
	
}

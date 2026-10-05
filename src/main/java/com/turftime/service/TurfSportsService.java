package com.turftime.service;

import java.util.List;

import com.turftime.dto.TurfSportCreateDTO;
import com.turftime.dto.TurfSportResponseDTO;
import com.turftime.dto.TurfSportUpdateDTO;

public interface TurfSportsService  {

	
	TurfSportResponseDTO createTurfSports(TurfSportCreateDTO dto);
	
	List<TurfSportResponseDTO> getAllTurfSport(Long id);
	
	TurfSportResponseDTO updateTurfSportPrice(TurfSportUpdateDTO dto);
	
	void deleteTurfSport(Long turfId, Long turfSportId);
	
	String deleteAllTurfSports(Long turfId);
	
}

package com.turftime.service;

import java.util.List;

import com.turftime.dto.SportCreateDTO;
import com.turftime.dto.SportResponseDTO;
import com.turftime.dto.SportUpdateDTO;

public interface SportsService {

	SportResponseDTO createSport(SportCreateDTO dto);

	List<SportResponseDTO> getAllSport();

	String deleteAllSport();
	
	SportResponseDTO updateSport(Long sportId, SportUpdateDTO dto);
}

package com.turftime.service;

import java.util.List;

import com.turftime.dto.SportCreateDTO;
import com.turftime.dto.SportResponseDTO;

public interface SportsService {

	SportResponseDTO createSport(SportCreateDTO dto);
	
	List<SportResponseDTO> getAllSport();
}

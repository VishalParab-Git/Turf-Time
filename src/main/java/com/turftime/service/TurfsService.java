package com.turftime.service;

import java.util.List;

import com.turftime.dto.TurfCreateDTO;
import com.turftime.dto.TurfResponseDTO;

public interface TurfsService {

	TurfResponseDTO createTurf(TurfCreateDTO dto);
	
	TurfResponseDTO getTurf();
	
	List<TurfResponseDTO> findAllTurfsByCityorState(String city, String state);
	
	
	

}

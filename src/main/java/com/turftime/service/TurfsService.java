package com.turftime.service;

import java.util.List;

import com.turftime.dto.TurfCreateDTO;
import com.turftime.dto.TurfResponseDTO;
import com.turftime.dto.TurfUpdateDTO;

public interface TurfsService {

	TurfResponseDTO createTurf(TurfCreateDTO dto);
	
	List<TurfResponseDTO> getMyTurf();
	
	List<TurfResponseDTO> findAllTurfsByCityorState(String city, String state);
	
	TurfResponseDTO updateTurf(Long turfId, TurfUpdateDTO dto);
	
	void deleteTurfAccount(Long turfId);
	
	
	

}

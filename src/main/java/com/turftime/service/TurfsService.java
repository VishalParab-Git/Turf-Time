package com.turftime.service;

import com.turftime.dto.TurfCreateDTO;
import com.turftime.dto.TurfResponseDTO;

public interface TurfsService {

	TurfResponseDTO createTurf(TurfCreateDTO dto);
	
	TurfResponseDTO getTurf();

}

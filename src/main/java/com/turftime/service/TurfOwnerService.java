package com.turftime.service;

import com.turftime.dto.TurfOwnerCreateDTO;
import com.turftime.dto.TurfOwnerResponseDTO;

public interface TurfOwnerService {

	TurfOwnerResponseDTO createOwner( TurfOwnerCreateDTO dto);
	
	TurfOwnerResponseDTO currentOwner();

}

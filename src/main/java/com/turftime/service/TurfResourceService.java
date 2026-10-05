package com.turftime.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turftime.dto.TurfResourceCreateDTO;
import com.turftime.dto.TurfResourceResponseDTO;
import com.turftime.dto.TurfResourceUpdateDTO;

@Service
public interface TurfResourceService {

	TurfResourceResponseDTO createTurfResource(TurfResourceCreateDTO dto);
	
	TurfResourceResponseDTO getTurfResourceById(Long turfId, Long turfResourceId);
	
	List<TurfResourceResponseDTO> getAllTurfResource(Long turfId);
	
	TurfResourceResponseDTO updateTurfResource(Long turfId, Long resourceId, TurfResourceUpdateDTO dto);
	
	String deleteTurfResource(Long turfId, Long resourceId);
}

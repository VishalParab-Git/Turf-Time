package com.turftime.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.turftime.dto.TurfOwnerCreateDTO;
import com.turftime.dto.TurfOwnerResponseDTO;
import com.turftime.entity.TurfOwnersEntity;
import com.turftime.entity.UserEntity;
import com.turftime.mappers.TurfOwnersMapper;
import com.turftime.repository.TurfOwnerRepository;
import com.turftime.repository.UserRepository;
import com.turftime.service.TurfOwnerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TurfOwnerServiceImpl implements TurfOwnerService {

	private final TurfOwnerRepository ownerRepository;
	private final TurfOwnersMapper ownerMapper;
	private final UserRepository userRepository;
	
	@Override
	public TurfOwnerResponseDTO createOwner(TurfOwnerCreateDTO dto) {

		TurfOwnersEntity owner=ownerMapper.toEntity(dto);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		owner.setUser(user);
		ownerRepository.save(owner);
		
		return ownerMapper.toDTO(owner);
	}
	
	@Override
	public TurfOwnerResponseDTO currentOwner() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		TurfOwnersEntity owner=ownerRepository.findByUserUsername(username)
				.orElseThrow(()->new RuntimeException("Turf Owner not found"));
		
		return ownerMapper.toDTO(owner);
	}
	
	
}

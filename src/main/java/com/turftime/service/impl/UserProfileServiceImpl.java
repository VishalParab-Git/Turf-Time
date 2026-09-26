package com.turftime.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.turftime.dto.UserProfileCreateDTO;
import com.turftime.dto.UserProfileResponseDTO;
import com.turftime.entity.UserEntity;
import com.turftime.entity.UserProfileEntity;
import com.turftime.mappers.UserProfileMapper;
import com.turftime.repository.UserProfileRepository;
import com.turftime.repository.UserRepository;
import com.turftime.service.UserProfileService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

	private final UserProfileMapper profileMapper;
	private final UserProfileRepository profileRepository;
	private final UserRepository userRepository;

	@Override
	public UserProfileResponseDTO createProfile(UserProfileCreateDTO dto) {

		UserProfileEntity profile = profileMapper.toEntity(dto);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		profile.setUser(user);
		profileRepository.save(profile);

		return profileMapper.toDTO(profile);
	}

	@Override
	public UserProfileResponseDTO getProfile() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		UserProfileEntity profile=profileRepository.findByUserUsername(username)
				.orElseThrow(()-> new RuntimeException("User not Found"));
		
		return profileMapper.toDTO(profile);
	}
	
	
}

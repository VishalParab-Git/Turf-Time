package com.turftime.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turftime.dto.UserProfileCreateDTO;
import com.turftime.dto.UserProfileResponseDTO;
import com.turftime.dto.UserProfileUpdateDTO;
import com.turftime.entity.UserEntity;
import com.turftime.entity.UserProfileEntity;
import com.turftime.mappers.UserProfileMapper;
import com.turftime.repository.UserProfileRepository;
import com.turftime.repository.UserRepository;
import com.turftime.service.UserProfileService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	private final UserProfileMapper profileMapper;
	private final UserProfileRepository profileRepository;
	private final UserRepository userRepository;

	//create User-profile
	@Override
	public UserProfileResponseDTO createProfile(UserProfileCreateDTO dto) {

		UserProfileEntity profile = profileMapper.toEntity(dto);

		//find current user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		profile.setUser(user);
		profileRepository.save(profile);

		return profileMapper.toDTO(profile);
	}

	
	//get current User Profile
	@Override
	public UserProfileResponseDTO getProfile() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		UserProfileEntity profile=profileRepository.findByUserUsername(username)
				.orElseThrow(()-> new RuntimeException("User not Found"));
		
		return profileMapper.toDTO(profile);
	}
	

	//Update current user Profile
	@Override
	public UserProfileResponseDTO updateUserProfile(UserProfileUpdateDTO user) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		UserProfileEntity existingProfile=profileRepository.findByUserUsername(username)
				.orElseThrow(()-> new RuntimeException("User not Found"));
		
		profileMapper.updateProfileDTOToEntity(user, existingProfile);
		
	    UserProfileEntity updated =profileRepository.save(existingProfile);
		
		return profileMapper.toDTO(updated);
	}
	
	
}

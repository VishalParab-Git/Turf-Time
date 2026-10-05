package com.turftime.service;

import com.turftime.dto.UserProfileCreateDTO;
import com.turftime.dto.UserProfileResponseDTO;
import com.turftime.dto.UserProfileUpdateDTO;

public interface UserProfileService {

	UserProfileResponseDTO createProfile( UserProfileCreateDTO dto);

	UserProfileResponseDTO getProfile();

	UserProfileResponseDTO updateUserProfile(UserProfileUpdateDTO user);
	
	
}

package com.turftime.service;

import com.turftime.dto.UserProfileCreateDTO;
import com.turftime.dto.UserProfileResponseDTO;

public interface UserProfileService {

	UserProfileResponseDTO createProfile( UserProfileCreateDTO dto);

	UserProfileResponseDTO getProfile();

}

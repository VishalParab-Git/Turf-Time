package com.turftime.service;

import com.turftime.dto.UserRegisterDTO;
import com.turftime.dto.UserResponseDTO;

public interface UserService {

	public UserResponseDTO registerUser(UserRegisterDTO dto);
	
}

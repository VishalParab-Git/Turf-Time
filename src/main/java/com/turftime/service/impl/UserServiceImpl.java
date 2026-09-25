package com.turftime.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.turftime.dto.UserRegisterDTO;
import com.turftime.dto.UserResponseDTO;
import com.turftime.entity.UserEntity;
import com.turftime.enums.UserRole;
import com.turftime.enums.UserStatus;
import com.turftime.mappers.UserMapper;
import com.turftime.repository.UserRepository;
import com.turftime.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserResponseDTO registerUser(UserRegisterDTO dto) {
		
		UserEntity user=userMapper.toEntity(dto);
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(UserRole.PLAYER);
		user.setStatus(UserStatus.ACTIVE);
		userRepository.save(user);
		
		return userMapper.toDTO(user);
	}
	
	
	
}

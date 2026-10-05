package com.turftime.service.impl;

import java.util.HashSet;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.turftime.dto.ChangePasswordRequestDTO;
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

	//register new user
	@Override
	public UserResponseDTO registerUser(UserRegisterDTO dto) {
		
		UserEntity user=userMapper.toEntity(dto);
		
		// Ensure role is initialized
	    if (user.getRole() == null) {
	        user.setRole(new HashSet<>());
	    }
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.getRole().add(UserRole.PLAYER);
		user.setStatus(UserStatus.ACTIVE);
		userRepository.save(user);
		
		return userMapper.toDTO(user);
	}

	//delete current user account
	@Override
	public void deleteMyAccount() {
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userName=authentication.getName();
		
		UserEntity user=userRepository.findByUsername(userName)
				.orElseThrow(()->new RuntimeException("User not found"));
		
		user.setStatus(UserStatus.DELETED);
		userRepository.save(user);
		
	}

	//change user password
	@Override
	public String changePassword(ChangePasswordRequestDTO dto) {
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userName=authentication.getName();
		
		UserEntity user=userRepository.findByUsername(userName)
				.orElseThrow(()->new RuntimeException("User not found"));
				
		if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
	        throw new RuntimeException("Invalid Password");
	    }
		
		if(!dto.getNewPassword().equals(dto.getConfirmPassword())) {
			throw new RuntimeException("Invalid Password");
		}
		
		user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
		
		userRepository.save(user);
		return "Password change successfully";
	}
	
	
	
}

package com.turftime.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.turftime.entity.UserEntity;
import com.turftime.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user=userRepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
	
		
		
		  return org.springframework.security.core.userdetails.User
	                .withUsername(user.getUsername())
	                .password(user.getPassword())
	                .authorities( user.getRole().stream()
	                        .map(role -> "ROLE_" + role.name())
	                        .toArray(String[]::new))
	                .build();
	}

}

package com.turftime.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtService jwtService;
	

	    public String login(
	            String username,
	            String password) {

	        Authentication authentication =
	                authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                        username,
	                        password
	                    )
	                );

	        UserDetails userDetails =
	                (UserDetails) authentication.getPrincipal();

	        return jwtService.generateToken(userDetails);
	    }

}

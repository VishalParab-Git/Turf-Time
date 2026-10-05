package com.turftime.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig  {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.csrf(csrf-> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				
				.requestMatchers("/api/v1/auth/**").permitAll()
				
				.requestMatchers("/api/v1/user-profile/**").hasAnyRole("PLAYER","ADMIN")	//ok
				
				.requestMatchers("/api/v1/users/me/**").hasAnyRole("PLAYER","ADMIN")		//ok
								
				.requestMatchers("/api/v1/owners/**").hasAnyRole("TURF_OWNER","ADMIN","PLAYER")	//ok
				
				.requestMatchers("/api/v1/turfs/**").hasAnyRole("TURF_OWNER","ADMIN","PLAYER")	//ok
												
				.requestMatchers("/api/v1/sports/**").hasAnyRole("PLAYER","TURF_OWNER","ADMIN")		//write only admin other only see	
				
				.requestMatchers("/api/v1/turf-sports/**").hasAnyRole("PLAYER","TURF_OWNER","ADMIN")	//ok
				
				.requestMatchers("/api/v1/turf-resources/**").hasAnyRole("PLAYER","TURF_OWNER","ADMIN")	//ok

				.requestMatchers("/api/v1/resource-sports/**").hasAnyRole("PLAYER","TURF_OWNER","ADMIN")	//ok
				
				.requestMatchers("/api/v1/bookings/**").hasAnyRole("PLAYER","TURF_OWNER","ADMIN")	//ok


				
				
				.anyRequest()
				.authenticated()
					)
			.sessionManagement(session->
				session.sessionCreationPolicy(
							SessionCreationPolicy.STATELESS
						)
					)
				.addFilterBefore(jwtAuthenticationFilter, 
						UsernamePasswordAuthenticationFilter.class);
				
				
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}

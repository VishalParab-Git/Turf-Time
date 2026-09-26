package com.turftime.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {
	
	@Value("${jwt.secret}")
	private String secret;
	
	private SecretKey getSigningKey() {
		
		byte[] keyBytes=Decoders.BASE64.decode(secret);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(UserDetails userDetails) {
		
		return Jwts.builder()
				.subject(userDetails.getUsername())
				.issuedAt(new Date())
				.expiration(
						new Date(
						System.currentTimeMillis()
						+ 1000 * 60 * 15
						)
				)
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();

	}
	
	
	public String extractUsername(String token) {
		
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		
		String username= extractUsername(token);
		
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
			
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token)
				.before(new Date());
	}
	

	private Date extractExpiration(String token) {
		
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getExpiration();
	}
	
}

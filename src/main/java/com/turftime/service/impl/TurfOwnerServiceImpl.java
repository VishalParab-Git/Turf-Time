package com.turftime.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turftime.dto.TurfOwnerCreateDTO;
import com.turftime.dto.TurfOwnerProfileUpdateDTO;
import com.turftime.dto.TurfOwnerResponseDTO;
import com.turftime.entity.TurfOwnersEntity;
import com.turftime.entity.UserEntity;
import com.turftime.enums.OwnerStatus;
import com.turftime.enums.UserRole;
import com.turftime.mappers.TurfOwnersMapper;
import com.turftime.repository.TurfOwnerRepository;
import com.turftime.repository.UserRepository;
import com.turftime.service.TurfOwnerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class TurfOwnerServiceImpl implements TurfOwnerService {

	private final TurfOwnerRepository ownerRepository;
	private final TurfOwnersMapper ownerMapper;
	private final UserRepository userRepository;
	
	//Create Owner Profile/Account
	@Override
	public TurfOwnerResponseDTO createOwner(TurfOwnerCreateDTO dto) {

		TurfOwnersEntity owner=ownerMapper.toEntity(dto);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		

		if (ownerRepository.existsByUser(user)  ) {
			
			TurfOwnersEntity turfOwner=ownerRepository.findByUserUsername(username)
					.orElseThrow(() -> new RuntimeException("Turf Owner not found"));
			
			if(turfOwner.getStatus().equals(OwnerStatus.DELETED)) {
				
				 // Add Turf Owner role if not already present
				if (!user.getRole().contains(UserRole.TURF_OWNER)) {
				    user.getRole().add(UserRole.TURF_OWNER);
				    userRepository.save(user);
				}

				turfOwner.setUser(user);
				turfOwner.setStatus(OwnerStatus.ACTIVE);
			
				ownerRepository.save(turfOwner);
				
				return ownerMapper.toDTO(turfOwner);
			}
			
		    throw new IllegalStateException("User already has a Turf Owner account");
		}
		
		
	    // Add Turf Owner role if not already present
		if (!user.getRole().contains(UserRole.TURF_OWNER)) {
		    user.getRole().add(UserRole.TURF_OWNER);
		    userRepository.save(user);
		}

		owner.setUser(user);
		owner.setStatus(OwnerStatus.ACTIVE);
	
		ownerRepository.save(owner);
		
		return ownerMapper.toDTO(owner);
	}
	
	//get Current Owner Information
	@Override
	public TurfOwnerResponseDTO currentOwner() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		TurfOwnersEntity owner=ownerRepository.findByUserUsername(username)
				.orElseThrow(()->new RuntimeException("Turf Owner not found"));
		
		return ownerMapper.toDTO(owner);
	}

	//update Turf Owner
	@Override
	public TurfOwnerResponseDTO updateTurfOwnerProfile(TurfOwnerProfileUpdateDTO dto) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		TurfOwnersEntity existingOwner=ownerRepository.findByUserUsername(username)
				.orElseThrow(()->new RuntimeException("Turf Owner not found"));
		
		ownerMapper.updateTurfOwnerProfileToEntity(dto, existingOwner);
		
		TurfOwnersEntity owner=ownerRepository.save(existingOwner);
		
		return ownerMapper.toDTO(owner);
	}

	//Delete current TurfOwnerAccount
	@Override
	public void deleteTurfOwnerAccount() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		TurfOwnersEntity owner=ownerRepository.findByUserUsername(username)
				.orElseThrow(()->new RuntimeException("Turf Owner not found"));
		owner.getUser().getRole().remove(UserRole.TURF_OWNER);
		owner.setStatus(OwnerStatus.DELETED);
		ownerRepository.save(owner);
		
	}
	
	
}

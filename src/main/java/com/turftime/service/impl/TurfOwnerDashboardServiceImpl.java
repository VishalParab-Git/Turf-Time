package com.turftime.service.impl;

import org.springframework.stereotype.Service;

import com.turftime.dto.OwnerRevenueSummaryResponseDTO;
import com.turftime.service.TurfOwnerDashboardService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TurfOwnerDashboardServiceImpl implements TurfOwnerDashboardService{@Override
	public OwnerRevenueSummaryResponseDTO getOwnerRevenueSummary() {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * private final BookingRepository bookingRepository; private final
	 * TurfRepository turfRepository;
	 * 
	 * @Override public OwnerRevenueSummaryResponseDTO getOwnerRevenueSummary() {
	 * 
	 * Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication(); String username =
	 * authentication.getName();
	 * 
	 * return null; }
	 */
	
}

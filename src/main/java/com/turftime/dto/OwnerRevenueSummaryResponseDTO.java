package com.turftime.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerRevenueSummaryResponseDTO {

	private long turfCount;
	
	private long bookingCount;
	
	private BigDecimal todayCollection;
	
	private BigDecimal weeklyCollection;
	
	private BigDecimal monthlyCollection;
}

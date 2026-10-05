package com.turftime.service;

import com.turftime.dto.OwnerRevenueSummaryResponseDTO;

public interface TurfOwnerDashboardService {

	OwnerRevenueSummaryResponseDTO getOwnerRevenueSummary();
	
	
	
	
/*
		🎯 OwnerDashboardService Tasks
		Revenue Summaries

		Calculate today’s collection (sum of bookings for owner’s turfs).

		Calculate weekly/monthly collection.

		Calculate lifetime/total revenue.

		Turf Management

		Count how many turfs belong to the owner.

		Show active vs inactive turfs.

		Show turfs under maintenance.

		Booking Statistics

		Count total bookings today, this week, this month.

		Show cancelled vs confirmed bookings.

		Show peak hours or slot utilization.

		Resource/Sport Statistics

		Count resources per turf (courts, grounds, rooms).

		Show active vs inactive turf sports.

		Pricing summary per sport.

		User Engagement

		Number of unique players who booked owner’s turfs.

		Repeat customers vs new customers.
		
*/
	
}

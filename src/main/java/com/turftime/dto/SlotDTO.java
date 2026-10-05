package com.turftime.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlotDTO {

	private LocalTime startTime;
	private LocalTime endTime;

	// Optional: duration in minutes if you want quick calculations
	private int durationMinutes;

}

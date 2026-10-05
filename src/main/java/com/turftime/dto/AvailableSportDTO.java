package com.turftime.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSportDTO {

	 private Long turfId;
	    private String turfName;
	    private Long resourceId;
	    private String resourceName;
	    private String sportName;

	    // Slot info
	    private LocalTime turfOpean;
	    private LocalTime turfClosed;

	    List<SlotDTO> slotsAvailable;
	
	
}

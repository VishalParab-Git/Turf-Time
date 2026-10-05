package com.turftime.service.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.turftime.dto.SlotDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TurfSlotService {
    
    public List<SlotDTO> generateSlots(
            LocalTime openingTime,
            LocalTime closingTime,
            int slotDurationMinutes) {

        List<SlotDTO> slots = new ArrayList<>();

        LocalTime current = openingTime;

        while (!current.plusMinutes(slotDurationMinutes)
                .isAfter(closingTime)) {

            LocalTime end =
                    current.plusMinutes(slotDurationMinutes);

            SlotDTO slot = new SlotDTO();
            slot.setStartTime(current);
            slot.setEndTime(end);

            slots.add(slot);

            current = end;
        }

        return slots;
    }
}
package com.turftime.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class CodeGeneratorService {
	
	
	// Counter for daily bookings
    private final AtomicInteger bookingCounter = new AtomicInteger(1);

    // Track the last date used
    private LocalDate lastGeneratedDate = LocalDate.now();

    // Prefix constant for Booking
    public static final String BOOKING_PREFIX = "BK";
    
    /**
     * Generate booking code.
     * Example: BK-20260921-0001
     */
    public synchronized String generateBookingCode() {
        LocalDate today = LocalDate.now();

        // Reset counter if date changed
        if (!today.equals(lastGeneratedDate)) {
            bookingCounter.set(1);
            lastGeneratedDate = today;
        }

        int sequence = bookingCounter.getAndIncrement();

        // Format date as YYYYMMDD
        String datePart = today.format(DateTimeFormatter.BASIC_ISO_DATE);

        // Build final code
        return BOOKING_PREFIX + "-" + datePart + "-" + String.format("%04d", sequence);
    }
}

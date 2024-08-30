package com.valuelabs.tracking_number_generator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class TrackingNumberServiceTests {

    private TrackingNumberService trackingNumberService;

    @BeforeEach
    void setUp() {
        trackingNumberService = new TrackingNumberService();
    }

    @Test
    void testGenerateTrackingNumberIsUnique() {
        Set<String> generatedNumbers = new HashSet<>();
        int numberOfAttempts = 1000; // Number of unique tracking numbers to test

        for (int i = 0; i < numberOfAttempts; i++) {
            String trackingNumber = trackingNumberService.generateTrackingNumber();
            assertTrue(trackingNumber.matches("^[A-Z0-9]{1,16}$"));
            assertFalse(generatedNumbers.contains(trackingNumber));
            generatedNumbers.add(trackingNumber);
        }
    }

    @Test
    void testGenerateTrackingNumberIsValid() {
        String trackingNumber = trackingNumberService.generateTrackingNumber();
        assertNotNull(trackingNumber);
        assertTrue(trackingNumber.matches("^[A-Z0-9]{1,16}$"));
    }
}


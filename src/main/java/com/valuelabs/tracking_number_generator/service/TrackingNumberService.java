package com.valuelabs.tracking_number_generator.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TrackingNumberService {

    private final AtomicInteger counter = new AtomicInteger();
    private final ConcurrentHashMap<String, Boolean> generatedTrackingNumbers = new ConcurrentHashMap<>();

    public String generateTrackingNumber() {
        String trackingNumber;
        do {
            trackingNumber = generateRandomTrackingNumber();
        } while (generatedTrackingNumbers.putIfAbsent(trackingNumber, Boolean.TRUE) != null);
        return trackingNumber;
    }

    private String generateRandomTrackingNumber() {
        int number = counter.incrementAndGet();
        return String.format("%04X%04X", number, (int) (Math.random() * 0xFFFF));
    }
}
package com.valuelabs.tracking_number_generator.controller;

import com.valuelabs.tracking_number_generator.service.TrackingNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrackingNumberController.class)
public class TrackingNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrackingNumberService trackingNumberService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new TrackingNumberController(trackingNumberService)).build();
    }

    @Test
    public void testGetNextTrackingNumber() throws Exception {

        String expectedTrackingNumber = "ABC1234567890123";
        when(trackingNumberService.generateTrackingNumber()).thenReturn(expectedTrackingNumber);

        mockMvc.perform(get("/next-tracking-number")
                        .param("origin_country_id", "MY")
                        .param("destination_country_id", "ID")
                        .param("weight", "1.234")
                        .param("created_at", "2018-11-20T19:29:32+08:00")
                        .param("customer_id", "de619854-b59b-425e-9db4-943979e1bd49")
                        .param("customer_name", "RedBox Logistics")
                        .param("customer_slug", "redbox-logistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trackingNumber").value(expectedTrackingNumber))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void testGetNextTrackingNumberEmptyResponse() throws Exception {
        when(trackingNumberService.generateTrackingNumber()).thenReturn("");

        mockMvc.perform(get("/next-tracking-number")
                        .param("origin_country_id", "US")
                        .param("destination_country_id", "CA")
                        .param("weight", "2.345")
                        .param("created_at", DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC).format(Instant.now()))
                        .param("customer_id", "123e4567-e89b-12d3-a456-426614174000")
                        .param("customer_name", "Test Customer")
                        .param("customer_slug", "test-customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trackingNumber").value(""))
                .andExpect(jsonPath("$.createdAt").exists());
    }
}

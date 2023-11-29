package com.project.SafetyNet.controller;

import com.jsoniter.output.JsonStream;
import com.project.SafetyNet.model.Firestation;
import com.project.SafetyNet.service.FirestationService;
import com.project.SafetyNet.service.FirestationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = FirestationController.class)
public class FirestationControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    FirestationService firestationService;
    @InjectMocks
    FirestationController firestationController;

    @BeforeEach
    void setup() {
    }


    @Test
    void getAllFirestationsTest() throws Exception {
        List<Firestation> firestations = Arrays.asList(
                new Firestation("1509 Culver St", "3"),
                new Firestation("29 15th St", "2")
        );
        when(firestationService.getAllFirestation()).thenReturn(firestations);

        mockMvc.perform(get("/SafetyNet/firestation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(firestations.size()));
    }

    @Test
    void addFirestationTest() throws Exception {
        Firestation firestation = new Firestation("1509 Culver St", "3");
        when(firestationService.addFirestation(firestation)).thenReturn(firestation);

        mockMvc.perform(post("/SafetyNet/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStream.serialize(firestation)))
                .andExpect(status().isCreated());

        verify(firestationService, times(1)).addFirestation(eq(firestation));
    }

    @Test
    void updateFirestationTest() throws Exception {
        String address = "1509 Culver St";
        Firestation firestation = new Firestation("1509 Culver St", "3");

        when(firestationService.updateFirestation(address, firestation)).thenReturn(firestation);

        mockMvc.perform(put("/SafetyNet/firestation")
                        .param("address", "1509 Culver St")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStream.serialize(firestation)))
                .andExpect(status().isOk());

        verify(firestationService, times(1)).updateFirestation(eq("1509 Culver St"), eq(firestation));
    }

    @Test
    void deleteFirestationTest() throws Exception {
        mockMvc.perform(delete("/SafetyNet/firestation")
                        .param("address", "1509 Culver St"))
                .andExpect(status().isGone());

        verify(firestationService, times(1)).deleteFirestation(eq("1509 Culver St"));
    }


}
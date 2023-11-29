package com.project.SafetyNet.controller;

import com.project.SafetyNet.controller.dto.*;
import com.project.SafetyNet.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(URLController.class)

class URLControlleTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private FirestationService firestationService;

    @MockBean
    private PersonService personService;
    @MockBean
    private MedicalRecordService medicalRecordService;
    @MockBean
    private URLService urlService;

    @InjectMocks
    private URLController urlController;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getPersonCoveredByFirestationTest() throws Exception {
        String station = "1";
        when(urlService.getPeopleCoveredByFirestation(station)).thenReturn(new CoveredPeople());
        mockMvc.perform(MockMvcRequestBuilders.get("/firestation").contentType(MediaType.APPLICATION_JSON)
                .param("station", "1")).andExpect(status().isOk());


    }

    @Test
    void getChildByAddressTest() throws Exception {
        String address = "1509 Culver St";
        when(urlService.getChildByAddress(address)).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/childAlert").contentType(MediaType.APPLICATION_JSON)
                .param("address", "1509 Culver St")).andExpect(status().isOk());

               verify(urlService, times(1)).getChildByAddress(address);
    }

    @Test
    void getPhoneByStationTest() throws Exception {
        String station = "1";
        when(urlService.getPhoneByStation(station)).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert").contentType(MediaType.APPLICATION_JSON)
                .param("station", "1")).andExpect(status().isOk());

    }
    @Test
    void  getPersonByAddressTest() throws Exception {
        String address = "1509 Culver St";
        when(urlService.getPersonByAddress(address)).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/fire").contentType(MediaType.APPLICATION_JSON)
                .param("address", "1509 Culver St")).andExpect(status().isOk());

    }
    @Test
    void getPersonByStationTest() throws Exception {
        String station = "1";
        when(urlService.getPersonByStation(station)).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/flood").contentType(MediaType.APPLICATION_JSON)
                .param("station", "1"))
                .andExpect(status().isOk());
    }
    @Test
    void getPersonInfoTest() throws Exception {
        String firstName="Toto";
        String lastName="Tutu";
        when(urlService.getPersonInfo(firstName,lastName)).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/personInfo").contentType(MediaType.APPLICATION_JSON)
                .param("firstName", "Toto").param("lastName", "Tutu")).andExpect(status().isOk());
    }
    @Test
    void getEmailsByCity() throws Exception {
        String city="Culver";
        when(urlService.getEmailsByCity(city)).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail").contentType(MediaType.APPLICATION_JSON)
                .param("city", "Culver")).andExpect(status().isOk());

    }
}


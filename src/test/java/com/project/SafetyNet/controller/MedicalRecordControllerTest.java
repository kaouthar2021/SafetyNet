package com.project.SafetyNet.controller;

import com.jsoniter.output.JsonStream;
import com.project.SafetyNet.controller.MedicalRecordController;
import com.project.SafetyNet.model.MedicalRecord;
import com.project.SafetyNet.service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MedicalRecordController.class)
class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @InjectMocks
    private MedicalRecordController medicalRecordController;

    @Test
    void getAllMedicalRecordTest() throws Exception {
        MedicalRecord medicalRecordTest = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
                Arrays.asList("nillacilan"));
        List<MedicalRecord> medicalRecordList = new ArrayList<>(Arrays.asList(medicalRecordTest));
        when(medicalRecordService.getAllMedicalRecord()).thenReturn(medicalRecordList);

        mockMvc.perform(get("/SafetyNet/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$", hasSize(1))));

        verify(medicalRecordService, times(1)).getAllMedicalRecord();
    }

    @Test
    public void addMedicalRecordTest() throws Exception {
        MedicalRecord medicalRecord1 = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
                Arrays.asList("peanut"));

        when(medicalRecordService.addMedicalRecord(medicalRecord1)).thenReturn(medicalRecord1);


        mockMvc.perform(post("/SafetyNet/medicalRecord").content(JsonStream.serialize(medicalRecord1))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();


    }
    @Test
    public void updateMedicalRecordTest()throws Exception{
        MedicalRecord medicalRecord1 = new MedicalRecord("Jimmy", "Sax", "03/06/1980", Arrays.asList("aznol:350mg"),
                Arrays.asList("peanut"));
        when(medicalRecordService.updateMedicalRecord("Jimmy","Sax",medicalRecord1)).thenReturn(medicalRecord1);
        mockMvc.perform(MockMvcRequestBuilders.put("/SafetyNet/medicalRecord")
                        .param("firstName", "Jimmy")
                        .param("lastName", "Sax")
                        .content(JsonStream.serialize(medicalRecord1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecordTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/SafetyNet/medicalRecord")
                        .param("firstName", "Jimmy")
                        .param("lastName", "Sax")
                        .contentType(MediaType.APPLICATION_JSON))
                       .andExpect(status().isGone());

    }
    @Test
    void findByFirstLastNameTest() throws Exception {
        String firstName = "Jimmy";
        String lastName = "Sax";

        mockMvc.perform(get("/SafetyNet/medicalRecord/medical")
                .param("firstName", "Jimmy")
                .param("lastName", "Sax"))
                .andExpect(status().isOk());
    }

   }


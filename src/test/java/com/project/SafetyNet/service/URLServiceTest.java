package com.project.SafetyNet.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class URLServiceTest {
      @Autowired
    MockMvc mockMvc;
      @Mock
    PersonService personService;
      @Mock
    FirestationService firestationService;
      @Mock
    MedicalRecordService medicalRecordService;
      @Test
    public void getChildByAddressTest(){
          
      }

}

package com.project.SafetyNet.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class URLControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Mock
    private URLService urlService;

    @Test
    public void getChildByAdressTest() throws Exception{
        mockMvc.perform(get("/childAlert")
                .param("address","1509 Culver St"))
                .andExpect(status().isOk());
    }

    }



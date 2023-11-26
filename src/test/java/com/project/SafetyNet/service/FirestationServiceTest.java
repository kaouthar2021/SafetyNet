package com.project.SafetyNet.service;

import com.project.SafetyNet.repository.FirestationRepository;
import com.project.SafetyNet.model.Firestation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class FirestationServiceTest {

    @Mock
    private FirestationRepository firestationRepository;

    @InjectMocks
    private FirestationService firestationService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllFirestationTest() {

        List<Firestation> firestations = new ArrayList<>();
        when(firestationRepository.findAllFirestation()).thenReturn(firestations);

        List<Firestation> result = firestationService.getAllFirestation();

        verify(firestationRepository, times(1)).findAllFirestation();
        assertEquals(firestations, result);
    }

    @Test
    void addFirestationTest() {

        Firestation firestation = new Firestation();
        when(firestationRepository.addFirestation(firestation)).thenReturn(firestation);

        Firestation result = firestationService.addFirestation(firestation);

        verify(firestationRepository, times(1)).addFirestation(firestation);
        assertEquals(firestation, result);
    }

    @Test
    void updateFirestationTest() {

        String address = "1509 Culver St";
        Firestation firestation = new Firestation();
        when(firestationRepository.updateFirestation(address, firestation)).thenReturn(firestation);

        Firestation result = firestationService.updateFirestation(address, firestation);

        verify(firestationRepository, times(1)).updateFirestation(address, firestation);
        assertEquals(firestation, result);
    }

    @Test
    void deleteFirestationTest() {

        firestationService.deleteFirestation("1509 Culver St");

        verify(firestationRepository, times(1)).deleteFirestation("1509 Culver St");
    }


}

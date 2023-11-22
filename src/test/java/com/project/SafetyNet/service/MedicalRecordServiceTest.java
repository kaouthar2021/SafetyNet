package com.project.SafetyNet.service;

import com.project.SafetyNet.repository.MedicalRecordRepository;
import com.project.SafetyNet.model.MedicalRecord;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private MedicalRecordService medicalRecordService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllMedicalRecordTest() {

        List<MedicalRecord> medicalRecords = new ArrayList<>();
        when(medicalRecordRepository.findAllMedicalRecord()).thenReturn(medicalRecords);

        List<MedicalRecord> result = medicalRecordService.getAllMedicalRecord();

        verify(medicalRecordRepository, times(1)).findAllMedicalRecord();
        assertEquals(medicalRecords, result);
    }

    @Test
    void addMedicalRecordTest() {

        MedicalRecord medicalRecord = new MedicalRecord();
        when(medicalRecordRepository.addMedicalRecord(medicalRecord)).thenReturn(medicalRecord);


        MedicalRecord result = medicalRecordService.addMedicalRecord(medicalRecord);

        verify(medicalRecordRepository, times(1)).addMedicalRecord(medicalRecord);
        assertEquals(medicalRecord, result);
    }

    @Test
    void updateMedicalRecordTest() {

        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecord medicalRecord = new MedicalRecord();
        when(medicalRecordRepository.updateMedicalRecord(firstName, lastName, medicalRecord)).thenReturn(medicalRecord);

        MedicalRecord result = medicalRecordService.updateMedicalRecord(firstName, lastName, medicalRecord);

        verify(medicalRecordRepository, times(1)).updateMedicalRecord(firstName, lastName, medicalRecord);
        assertEquals(medicalRecord, result);
    }

    @Test
    void deleteMedicalRecord() {
        medicalRecordService.deleteMedicalRecord("John", "Boyd");

        verify(medicalRecordRepository, times(1)).deleteMedicalRepository("John", "Boyd");
    }

    @Test
    void findByFirstLastName() {
        String firstName = "John";
        String lastName = "Boyd";

        List<MedicalRecord> medicalRecords = new ArrayList<>();

        when(medicalRecordRepository.findAllMedicalRecord()).thenReturn(medicalRecords);
        String result = medicalRecordService.findByFirstLastName(firstName, lastName);

        verify(medicalRecordRepository, times(1)).findAllMedicalRecord();
    }


}


package com.project.SafetyNet.repository;

import com.project.SafetyNet.model.Data;
import com.project.SafetyNet.model.MedicalRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class MedicalRecordRepositoryTest {

    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private MedicalRecordRepositoryImpl medicalRecordRepository;
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();
    static {

        medicalRecords.add(new MedicalRecord("Clive", "Ferguson", "03/06/1994",
                new ArrayList<String>(List.of("ibupurin:200mg", "hydrapermazol:400mg")),
                new ArrayList<String>(List.of("nillacilan"))));
        medicalRecords.add(new MedicalRecord("John", "Boyd", "03/06/1984",
                new ArrayList<String>(List.of("aznol:350mg", "hydrapermazol:100mg")),
                new ArrayList<String>(List.of("nillacilan"))));
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void testFindAllMedicalRecord() {
        // Données de test
        Data data = new Data();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(new MedicalRecord("John", "Doe", "1990-01-01", new ArrayList<>(), new ArrayList<>()));
        data.setMedicalrecords(medicalRecords);

        // Configuration du mock
        when(dataRepository.getData()).thenReturn(data);

        // Appel de la méthode à tester
        List<MedicalRecord> result = medicalRecordRepository.findAllMedicalRecord();

        // Vérification du résultat
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe", result.get(0).getLastName());
        assertEquals("1990-01-01", result.get(0).getBirthdate());

        // Vérification que la méthode du mock a été appelée correctement
        verify(dataRepository, times(1)).getData();
    }

    @Test
    void testAddMedicalRecord() {
        // Données de test
       MedicalRecord medicalRecordToAdd = new MedicalRecord("Jane", "Smith", "1985-03-15", new ArrayList<>(), new ArrayList<>());

        // Configuration du mock
        Data testData = new Data();
        when(dataRepository.getData()).thenReturn(testData);


        // Appel de la méthode à tester
        MedicalRecord result = medicalRecordRepository.addMedicalRecord(medicalRecordToAdd);

        // Vérification du résultat
        assertEquals("Jane", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        assertEquals("1985-03-15", result.getBirthdate());

        // Vérification que la méthode du mock a été appelée correctement
        verify(dataRepository, times(1)).getData();
    }

    @Test
    void testUpdateMedicalRecord() {
        // Données de test
        Data data = new Data();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord existingMedicalRecord = new MedicalRecord("Jane", "Smith", "1985-03-15", new ArrayList<>(), new ArrayList<>());
        medicalRecords.add(existingMedicalRecord);
        data.setMedicalrecords(medicalRecords);

        MedicalRecord medicalRecordToUpdate = new MedicalRecord("Jane", "Smith", "1990-05-20", new ArrayList<>(), new ArrayList<>());

        // Configuration du mock
        when(dataRepository.getData()).thenReturn(data);

        // Appel de la méthode à tester
        MedicalRecord result = medicalRecordRepository.updateMedicalRecord("Jane", "Smith", medicalRecordToUpdate);

        // Vérification du résultat
        assertEquals("Jane", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        assertEquals("1990-05-20", result.getBirthdate());

        // Vérification que la méthode du mock a été appelée correctement
        verify(dataRepository, times(1)).getData();
    }

    @Test
    void testDeleteMedicalRecord() {
        // Données de test
        Data data = new Data();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(new MedicalRecord("John", "Doe", "1990-01-01", new ArrayList<>(), new ArrayList<>()));
        data.setMedicalrecords(medicalRecords);

        // Configuration du mock
        when(dataRepository.getData()).thenReturn(data);

        // Appel de la méthode à tester
        medicalRecordRepository.deleteMedicalRepository("John", "Doe");

        // Vérification du résultat
        assertEquals(0, data.getMedicalrecords().size());

        // Vérification que la méthode du mock a été appelée correctement
        verify(dataRepository, times(2)).getData();
    }
}


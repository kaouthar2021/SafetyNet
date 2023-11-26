package com.project.SafetyNet.model;

import com.project.SafetyNet.model.MedicalRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

public class MedicalRecordTest {

    @Test
    void testMedicalRecordConstructorAndGetterMethods() {
        // Création d'une instance de MedicalRecord
        MedicalRecord medicalRecord = new MedicalRecord("John", "Doe", "01/01/1990", Arrays.asList("Medication1", "Medication2"), Arrays.asList("Allergy1", "Allergy2"));

        // Vérification des méthodes getters
        assertEquals("John", medicalRecord.getFirstName());
        assertEquals("Doe", medicalRecord.getLastName());
        assertEquals("01/01/1990", medicalRecord.getBirthdate());
        assertEquals(Arrays.asList("Medication1", "Medication2"), medicalRecord.getMedications());
        assertEquals(Arrays.asList("Allergy1", "Allergy2"), medicalRecord.getAllergies());
    }

    @Test
    void testMedicalRecordDefaultConstructor() {
        // Création d'une instance de MedicalRecord en utilisant le constructeur par défaut
        MedicalRecord medicalRecord = new MedicalRecord();

        // Vérification que les champs sont initialisés à null ou une liste vide selon le type
        assertEquals(null, medicalRecord.getFirstName());
        assertEquals(null, medicalRecord.getLastName());
        assertEquals(null, medicalRecord.getBirthdate());
        assertEquals(null, medicalRecord.getMedications());
        assertEquals(null, medicalRecord.getAllergies());
    }


}


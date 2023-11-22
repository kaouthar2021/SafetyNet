package com.project.SafetyNet.controller.dto;

import com.project.SafetyNet.controller.dto.PersonHouseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonHouseDtoTest {

    @Test
    void testGettersAndSetters() {
        // Création d'un objet PersonHouseDto avec des valeurs fictives
        PersonHouseDto personHouseDto = new PersonHouseDto();
        personHouseDto.setFirstName("John");
        personHouseDto.setLastName("Doe");
        personHouseDto.setPhone("555-1234");
        personHouseDto.setAge(30);
        List<String> medications = new ArrayList<>();
        medications.add("Medication1");
        personHouseDto.setMedications(medications);
        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        personHouseDto.setAllergies(allergies);

        // Vérification des méthodes getters
        assertEquals("John", personHouseDto.getFirstName());
        assertEquals("Doe", personHouseDto.getLastName());
        assertEquals("555-1234", personHouseDto.getPhone());
        assertEquals(30, personHouseDto.getAge());
        assertEquals(medications, personHouseDto.getMedications());
        assertEquals(allergies, personHouseDto.getAllergies());

        // Vérification des méthodes setters
        PersonHouseDto updatedPersonHouseDto = new PersonHouseDto();
        updatedPersonHouseDto.setFirstName("Jane");
        updatedPersonHouseDto.setLastName("Smith");
        updatedPersonHouseDto.setPhone("555-5678");
        updatedPersonHouseDto.setAge(25);
        List<String> updatedMedications = new ArrayList<>();
        updatedMedications.add("Medication2");
        updatedPersonHouseDto.setMedications(updatedMedications);
        List<String> updatedAllergies = new ArrayList<>();
        updatedAllergies.add("Allergy2");
        updatedPersonHouseDto.setAllergies(updatedAllergies);

        // Vérification que les setters ont mis à jour les valeurs correctement
        assertEquals("Jane", updatedPersonHouseDto.getFirstName());
        assertEquals("Smith", updatedPersonHouseDto.getLastName());
        assertEquals("555-5678", updatedPersonHouseDto.getPhone());
        assertEquals(25, updatedPersonHouseDto.getAge());
        assertEquals(updatedMedications, updatedPersonHouseDto.getMedications());
        assertEquals(updatedAllergies, updatedPersonHouseDto.getAllergies());
    }


}


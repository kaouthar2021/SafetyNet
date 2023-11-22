package com.project.SafetyNet.controller.dto;

import com.project.SafetyNet.controller.dto.PersonInfoDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonInfoDtoTest {

    @Test
    void testGettersAndSetters() {
        // Création d'un objet PersonInfoDto avec des valeurs fictives
        PersonInfoDto personInfoDto = new PersonInfoDto();
        personInfoDto.setFirstName("John");
        personInfoDto.setLastName("Doe");
        personInfoDto.setAddress("123 Main St");
        personInfoDto.setEmail("john.doe@email.com");
        personInfoDto.setAge(30);
        List<String> medications = new ArrayList<>();
        medications.add("Medication1");
        personInfoDto.setMedications(medications);
        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        personInfoDto.setAllergies(allergies);

        // Vérification des méthodes getters
        assertEquals("John", personInfoDto.getFirstName());
        assertEquals("Doe", personInfoDto.getLastName());
        assertEquals("123 Main St", personInfoDto.getAddress());
        assertEquals("john.doe@email.com", personInfoDto.getEmail());
        assertEquals(30, personInfoDto.getAge());
        assertEquals(medications, personInfoDto.getMedications());
        assertEquals(allergies, personInfoDto.getAllergies());

        // Vérification des méthodes setters
        PersonInfoDto updatedPersonInfoDto = new PersonInfoDto();
        updatedPersonInfoDto.setFirstName("Jane");
        updatedPersonInfoDto.setLastName("Smith");
        updatedPersonInfoDto.setAddress("456 Oak St");
        updatedPersonInfoDto.setEmail("jane.smith@email.com");
        updatedPersonInfoDto.setAge(25);
        List<String> updatedMedications = new ArrayList<>();
        updatedMedications.add("Medication2");
        updatedPersonInfoDto.setMedications(updatedMedications);
        List<String> updatedAllergies = new ArrayList<>();
        updatedAllergies.add("Allergy2");
        updatedPersonInfoDto.setAllergies(updatedAllergies);

        // Vérification que les setters ont mis à jour les valeurs correctement
        assertEquals("Jane", updatedPersonInfoDto.getFirstName());
        assertEquals("Smith", updatedPersonInfoDto.getLastName());
        assertEquals("456 Oak St", updatedPersonInfoDto.getAddress());
        assertEquals("jane.smith@email.com", updatedPersonInfoDto.getEmail());
        assertEquals(25, updatedPersonInfoDto.getAge());
        assertEquals(updatedMedications, updatedPersonInfoDto.getMedications());
        assertEquals(updatedAllergies, updatedPersonInfoDto.getAllergies());
    }


}


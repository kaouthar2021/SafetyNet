package com.project.SafetyNet.controller.dto;

import com.project.SafetyNet.controller.dto.PersonAtAddressDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PersonAtAddressDtoTest {

    @Test
    void testGettersAndSetters() {
        // Création d'un objet PersonAtAddressDto avec des valeurs fictives
        PersonAtAddressDto personAtAddressDto = new PersonAtAddressDto();
        personAtAddressDto.setFirstName("John");
        personAtAddressDto.setLastName("Doe");
        personAtAddressDto.setPhone("555-1234");
        personAtAddressDto.setStation("Fire Station 1");
        personAtAddressDto.setAge(30);
        List<String> medications = new ArrayList<>();
        medications.add("Medication1");
        personAtAddressDto.setMedications(medications);
        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        personAtAddressDto.setAllergies(allergies);

        // Vérification des méthodes getters
        assertEquals("John", personAtAddressDto.getFirstName());
        assertEquals("Doe", personAtAddressDto.getLastName());
        assertEquals("555-1234", personAtAddressDto.getPhone());
        assertEquals("Fire Station 1", personAtAddressDto.getStation());
        assertEquals(30, personAtAddressDto.getAge());
        assertEquals(medications, personAtAddressDto.getMedications());
        assertEquals(allergies, personAtAddressDto.getAllergies());

        // Vérification des méthodes setters
        PersonAtAddressDto updatedPersonAtAddressDto = new PersonAtAddressDto();
        updatedPersonAtAddressDto.setFirstName("Jane");
        updatedPersonAtAddressDto.setLastName("Smith");
        updatedPersonAtAddressDto.setPhone("555-5678");
        updatedPersonAtAddressDto.setStation("Fire Station 2");
        updatedPersonAtAddressDto.setAge(25);
        List<String> updatedMedications = new ArrayList<>();
        updatedMedications.add("Medication2");
        updatedPersonAtAddressDto.setMedications(updatedMedications);
        List<String> updatedAllergies = new ArrayList<>();
        updatedAllergies.add("Allergy2");
        updatedPersonAtAddressDto.setAllergies(updatedAllergies);

        // Vérification que les setters ont mis à jour les valeurs correctement
        assertEquals("Jane", updatedPersonAtAddressDto.getFirstName());
        assertEquals("Smith", updatedPersonAtAddressDto.getLastName());
        assertEquals("555-5678", updatedPersonAtAddressDto.getPhone());
        assertEquals("Fire Station 2", updatedPersonAtAddressDto.getStation());
        assertEquals(25, updatedPersonAtAddressDto.getAge());
        assertEquals(updatedMedications, updatedPersonAtAddressDto.getMedications());
        assertEquals(updatedAllergies, updatedPersonAtAddressDto.getAllergies());
    }


}


package com.project.SafetyNet.controller.dto;

import com.project.SafetyNet.controller.dto.FloodDto;
import com.project.SafetyNet.controller.dto.PersonHouseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class FloodDtoTest {

    @Test
    void testGettersAndSetters() {
        // Création d'un objet FloodDto avec des valeurs fictives
        FloodDto floodDto = new FloodDto();
        floodDto.setAddress("123 Main St");
        List<PersonHouseDto> personHouseDtos = new ArrayList<>();
        PersonHouseDto personHouseDto = new PersonHouseDto();
        personHouseDto.setFirstName("John");
        personHouseDto.setLastName("Doe");
        personHouseDto.setPhone("555-1234");
        personHouseDtos.add(personHouseDto);
        floodDto.setPersonHouseDtos(personHouseDtos);

        // Vérification des méthodes getters
        assertEquals("123 Main St", floodDto.getAddress());
        assertEquals(personHouseDtos, floodDto.getPersonHouseDtos());

        // Vérification des méthodes setters
        FloodDto updatedFloodDto = new FloodDto();
        updatedFloodDto.setAddress("456 Oak St");
        List<PersonHouseDto> updatedPersonHouseDtos = new ArrayList<>();
        PersonHouseDto updatedPersonHouseDto = new PersonHouseDto();
        updatedPersonHouseDto.setFirstName("Jane");
        updatedPersonHouseDto.setLastName("Smith");
        updatedPersonHouseDto.setPhone("555-5678");
        updatedPersonHouseDtos.add(updatedPersonHouseDto);
        updatedFloodDto.setPersonHouseDtos(updatedPersonHouseDtos);

        // Vérification que les setters ont mis à jour les valeurs correctement
        assertEquals("456 Oak St", updatedFloodDto.getAddress());
        assertEquals(updatedPersonHouseDtos, updatedFloodDto.getPersonHouseDtos());
    }


}



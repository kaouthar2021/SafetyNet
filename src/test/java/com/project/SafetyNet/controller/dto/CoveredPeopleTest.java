package com.project.SafetyNet.controller.dto;

import com.project.SafetyNet.controller.dto.CoveredPeople;
import com.project.SafetyNet.controller.dto.PersonSummray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoveredPeopleTest {

    @Test
    void testGettersAndSetters() {
        // Création d'un objet CoveredPeople avec des valeurs fictives
        CoveredPeople coveredPeople = new CoveredPeople();
        coveredPeople.setNumberChild(3);
        coveredPeople.setNumberAdult(5);
        List<PersonSummray> people = Arrays.asList(new PersonSummray(), new PersonSummray());
        coveredPeople.setPeople(people);

        // Vérification des méthodes getters
        assertEquals(3, coveredPeople.getNumberChild());
        assertEquals(5, coveredPeople.getNumberAdult());
        assertEquals(people, coveredPeople.getPeople());

        // Vérification des méthodes setters
        CoveredPeople updatedCoveredPeople = new CoveredPeople();
        updatedCoveredPeople.setNumberChild(4);
        updatedCoveredPeople.setNumberAdult(6);
        List<PersonSummray> updatedPeople = Arrays.asList(new PersonSummray());
        updatedCoveredPeople.setPeople(updatedPeople);

        // Vérification que les setters ont mis à jour les valeurs correctement
        assertEquals(4, updatedCoveredPeople.getNumberChild());
        assertEquals(6, updatedCoveredPeople.getNumberAdult());
        assertEquals(updatedPeople, updatedCoveredPeople.getPeople());
    }


}


package com.project.SafetyNet.model;

import com.project.SafetyNet.model.Firestation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirestationTest {

    @Test
    void testFirestationConstructorAndGetterMethods() {
        // Création d'une instance de Firestation
        Firestation firestation = new Firestation("123 Main St", "Station1");

        // Vérification des méthodes getters
        assertEquals("123 Main St", firestation.getAddress());
        assertEquals("Station1", firestation.getStation());
    }

    @Test
    void testFirestationDefaultConstructor() {
        // Création d'une instance de Firestation en utilisant le constructeur par défaut
        Firestation firestation = new Firestation();

        // Vérification que les champs sont initialisés à null
        assertEquals(null, firestation.getAddress());
        assertEquals(null, firestation.getStation());
    }


}


package com.project.SafetyNet.exception;

import com.project.SafetyNet.exception.RessourceNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RessourceNotFoundExceptionTest {

    @Test
    void testRessourceNotFoundException() {
        // Création d'une instance de RessourceNotFoundException
        RessourceNotFoundException exception = new RessourceNotFoundException("Ressource not found");

        // Vérification du message d'erreur
        assertEquals("Ressource not found", exception.getMessage());
    }
}


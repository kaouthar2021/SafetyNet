package com.project.SafetyNet.model;

import com.project.SafetyNet.model.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

    @Test
    void testPersonConstructorAndGetterMethods() {
        // Création d'une instance de Person
        Person person = new Person("John", "Doe", "123 Main St", "City", "12345", "555-1234", "john.doe@email.com");

        // Vérification des méthodes getters
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals("123 Main St", person.getAddress());
        assertEquals("City", person.getCity());
        assertEquals("12345", person.getZip());
        assertEquals("555-1234", person.getPhone());
        assertEquals("john.doe@email.com", person.getEmail());
    }

    @Test
    void testPersonToString() {
        // Création d'une instance de Person
        Person person = new Person("Jane", "Smith", "456 Oak St", "City", "54321", "555-5678", "jane.smith@email.com");

        // Vérification de la méthode toString
        String expectedToString = "{\"firstName\"=\"Jane\", \"lastName\"=\"Smith\", \"address\"=\"456 Oak St\", \"city\"=\"City\", \"zip\"=\"54321\", \"phone\"=\"555-5678\", \"email\"=\"jane.smith@email.com\"}";
        assertEquals(expectedToString, person.toString());
    }


}

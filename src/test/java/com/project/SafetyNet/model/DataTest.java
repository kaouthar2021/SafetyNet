package com.project.SafetyNet.model;

import com.project.SafetyNet.model.Data;
import com.project.SafetyNet.model.Firestation;
import com.project.SafetyNet.model.MedicalRecord;
import com.project.SafetyNet.model.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

public class DataTest {

    @Test
    void testDataConstructorAndGetterMethods() {
        // Création d'une instance de Data
        Data data = new Data();

        // Création de données de test
        Person person = new Person("John", "Doe", "123 Main St", "City1", "12345", "123-456-7890", "john.doe@example.com");
        Firestation firestation = new Firestation("123 Main St", "Station1");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Doe", "01/01/1990", Arrays.asList("Medication1", "Medication2"), Arrays.asList("Allergy1", "Allergy2"));

        // Ajout des données de test à l'instance de Data
        data.getPersons().add(person);
        data.getFirestations().add(firestation);
        data.getMedicalrecords().add(medicalRecord);

        // Vérification des méthodes getters
        List<Person> persons = data.getPersons();
        assertEquals(1, persons.size());
        assertEquals(person, persons.get(0));

        List<Firestation> firestations = data.getFirestations();
        assertEquals(1, firestations.size());
        assertEquals(firestation, firestations.get(0));

        List<MedicalRecord> medicalrecords = data.getMedicalrecords();
        assertEquals(1, medicalrecords.size());
        assertEquals(medicalRecord, medicalrecords.get(0));
    }


}


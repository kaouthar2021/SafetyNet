package com.project.SafetyNet.repository;

import com.project.SafetyNet.model.Data;
import com.project.SafetyNet.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PersonRepositoryImplTest {

    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private PersonRepositoryImpl personRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testFindAllPerson() {
        Data data = new Data();
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", "Doe", "123 Street", "City", "12345", "123-456-7890", "john@example.com"));
        data.setPersons(persons);

        when(dataRepository.getData()).thenReturn(data);

        List<Person> result = personRepository.findAllPerson();

        assertEquals(persons, result);
    }

    @Test
    void testAddPerson() {
        // Mocking the data
        Data data = new Data();
        List<Person> persons = new ArrayList<>();
        data.setPersons(persons);

        when(dataRepository.getData()).thenReturn(data);

        // Testing the method
        Person personToAdd = new Person("Jane", "Doe", "456 Street", "City", "67890", "987-654-3210", "jane@example.com");
        Person result = personRepository.addPerson(personToAdd);

        // Verifying the result
        assertEquals(personToAdd, result);
        assertEquals(1, persons.size());
        assertTrue(persons.contains(personToAdd));
    }
    @Test
    void testUpdatePerson() {
        // Données de test
        Data data = new Data();
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", "Doe", "123 Street", "City", "12345", "123-456-7890", "john@example.com"));
        data.setPersons(persons);

        // Configuration du mock
        when(dataRepository.getData()).thenReturn(data);

        // Appel de la méthode à tester
        Person personToUpdate = new Person("John", "Doe", "456 New Street", "New City", "67890", "987-654-3210", "john@example.com");
        Person result = personRepository.updatePerson("John", "Doe", personToUpdate);

        // Vérification du résultat
        assertEquals(personToUpdate, result);
        assertEquals("456 New Street", result.getAddress());
        assertEquals("New City", result.getCity());
        assertEquals("67890", result.getZip());
        assertEquals("987-654-3210", result.getPhone());
        assertEquals("john@example.com", result.getEmail());

        // Vérification que les méthodes du mock ont été appelées correctement
        verify(dataRepository, times(1)).getData();
    }

    @Test
    void testDeletePerson() {
        // Données de test
        Data data = new Data();
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", "Doe", "123 Street", "City", "12345", "123-456-7890", "john@example.com"));
        data.setPersons(persons);

        // Configuration du mock
        when(dataRepository.getData()).thenReturn(data);

        // Appel de la méthode à tester
        personRepository.deletePerson("John", "Doe");

        // Vérification du résultat
        assertEquals(0, data.getPersons().size());

        // Vérification que les méthodes du mock ont été appelées correctement
        verify(dataRepository, times(2)).getData();
    }

}

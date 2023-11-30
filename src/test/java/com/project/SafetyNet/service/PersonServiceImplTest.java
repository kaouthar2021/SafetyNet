package com.project.SafetyNet.service;

import com.project.SafetyNet.exception.RessourceNotFoundException;
import com.project.SafetyNet.model.Person;
import com.project.SafetyNet.repository.PersonRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {
    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    PersonServiceImpl personService;

    @Mock
    private PersonRepositoryImpl personRepository;

    private List<Person> personList = new ArrayList<>();

    @Test
    public void getPersonTest() throws Exception {

        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
                "jaboyd@email.com");
        personList = Arrays.asList(person);

        when(personRepository.findAllPerson()).thenReturn(this.personList);

        List<Person> result = personService.getAllPersons();

        assertThat(result).isNotNull();
        assertEquals(result.get(0).getFirstName(), person.getFirstName());
    }
    @Test
    public void addPersonTest() throws RessourceNotFoundException {
        Person personToAdd = new Person();
        when(personRepository.addPerson(personToAdd)).thenReturn(personToAdd);

        Person result = personService.addPerson(personToAdd);


        assertEquals(personToAdd, result);
    }
    @Test
    void updatePersonTest() throws RessourceNotFoundException {

        String firstName = "John";
        String lastName = "Boyd";
        Person personToUpdate = new Person();

        when(personRepository.updatePerson(firstName, lastName, personToUpdate)).thenReturn(personToUpdate);


        Person result = personService.updatePerson(firstName, lastName, personToUpdate);

        assertEquals(personToUpdate, result);
    }
    @Test
    void deletePersonTest()  throws RessourceNotFoundException {

        String firstName = "John";
        String lastName = "Boyd";

        // Act
        assertDoesNotThrow(() -> personService.deletePerson(firstName, lastName));

        // Assert
        verify(personRepository, times(1)).deletePerson(firstName, lastName);

        // Vérifiez qu'il n'y a plus de personne avec le nom supprimé
        List<Person> allPersons = personRepository.findAllPerson();
        assertTrue(allPersons.stream().noneMatch(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)),
                "La personne devrait être supprimée");

    }
    @Test
    void testGetFamilyMembers() {
        // Mocking
        String firstName = "John";
        String lastName = "Boyd";

        List<Person> mockPersonList = new ArrayList<>();
        Person person1 = new Person();
        person1.setFirstName("Alice");
        person1.setLastName(lastName);

        Person person2 = new Person();
        person2.setFirstName("Bob");
        person2.setLastName(lastName);

        mockPersonList.add(person1);
        mockPersonList.add(person2);

        when(personRepository.findAllPerson()).thenReturn(mockPersonList);

        // Test
        List<String> result = personService.getFamilyMembers(firstName, lastName);

        // Verification
        assertEquals(2, result.size());
        assertTrue(result.contains("Alice"));
        assertTrue(result.contains("Bob"));
    }
}
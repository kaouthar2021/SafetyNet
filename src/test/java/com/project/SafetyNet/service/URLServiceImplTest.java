package com.project.SafetyNet.service;

import com.project.SafetyNet.controller.dto.*;
import com.project.SafetyNet.model.Firestation;
import com.project.SafetyNet.model.MedicalRecord;
import com.project.SafetyNet.model.Person;
import com.project.SafetyNet.repository.FirestationRepositoryImpl;
import com.project.SafetyNet.repository.MedicalRecordRepositoryImpl;
import com.project.SafetyNet.repository.PersonRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class URLServiceImplTest {

    @Mock
    private PersonRepositoryImpl personRepository;

    @Mock
    private MedicalRecordRepositoryImpl medicalRecordRepository;

    @Mock
    private FirestationRepositoryImpl firestationRepository;

    @Mock
    private FirestationServiceImpl firestationService;

    @Mock
    private PersonService personService;

    @Mock
    private MedicalRecordServiceImpl medicalRecordService;

    @InjectMocks
    private URLServiceImpl urlService;
    private static List<Person> persons = new ArrayList<>();
    static {
        persons.add(
                new Person("Jonny", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6512", "jaboyd@email.com"));
        persons.add(new Person("Gimmy", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6513", "drk@email.com"));
        persons.add(new Person("Mike", "Boyd", "1509 Culver st", "Culver", "97451", "841-874-6512", "reg@email.com"));
        persons.add(
                new Person("Tata", "Popo", "892 Downing Ct", "Culver", "97451", "841-874-7878", "gramps@email.com"));
    }

    private static List<Firestation> firestations = new ArrayList<>();
    static {
        firestations.add(new Firestation("112 Steppes Pl", "4"));
        firestations.add(new Firestation("947 E. Rose Dr", "2"));
        firestations.add(new Firestation("1509 Culver st", "1"));

    }

    private static List<MedicalRecord> medicalRecords = new ArrayList<>();
    static {

        medicalRecords.add(new MedicalRecord("Jonny", "Boyd", "03/06/1984",
                new ArrayList<String>(List.of("aznol:350mg", "hydrapermazol:100mg")),
                new ArrayList<String>(List.of("illisoxian"))));
        medicalRecords.add(new MedicalRecord("Gimmy", "Boyd", "03/06/1989",
                new ArrayList<String>(List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg")),
                new ArrayList<String>()));
        medicalRecords.add(new MedicalRecord("Mike", "Boyd", "03/03/2015",
                new ArrayList<String>(List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg")),
                new ArrayList<String>()));
        medicalRecords.add(new MedicalRecord("Tata", "Popo", "03/04/2016",
                new ArrayList<String>(List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg")),
                new ArrayList<String>()));
    }
    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetPeopleCoveredByFirestation() {
        // Mock data
        String station = "1";

        // Mock repository calls
        when(firestationRepository.findAllFirestation()).thenReturn(firestations);
        when(personRepository.findAllPerson()).thenReturn(persons);
        when(medicalRecordRepository.findAllMedicalRecord()).thenReturn(medicalRecords);

        // Test the service method
        CoveredPeople coveredPeople = urlService.getPeopleCoveredByFirestation(station);

        // Verify the results
        assertEquals(3, coveredPeople.getPeople().size());
        assertEquals(2, coveredPeople.getNumberAdult());
        assertEquals(1, coveredPeople.getNumberChild());

        // Additional assertions, if needed
        // assertEquals(expectedValue, actualValue);

        // Verify that repository methods were called
        verify(firestationRepository, times(1)).findAllFirestation();
        verify(personRepository, times(1)).findAllPerson();
      //  verify(medicalRecordRepository, times(1)).findAllMedicalRecord();
    }


    @Test
    void testGetChildByAddress() {
        // Mock des données
        String address = "123 Main St";
        String firstName = "John";
        String lastName = "Doe";
        String birthdate = "01/01/2010";

        // Création d'un objet MedicalRecord pour un enfant
        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthdate, null, null);

        // Création d'un objet Person pour un enfant
        Person child = new Person(firstName, lastName, address, null, null, null, null);

        // Configurer le comportement du mock pour la recherche des personnes
        when(personRepository.findAllPerson()).thenReturn(Arrays.asList(child));

        // Configurer le comportement du mock pour la recherche des dossiers médicaux
        when(medicalRecordRepository.findAllMedicalRecord()).thenReturn(Arrays.asList(medicalRecord));

        // Appel de la méthode à tester
        List<ChildDto> result = urlService.getChildByAddress(address);

        // Vérification du résultat
        assertEquals(1, result.size());
        ChildDto childDto = result.get(0);
        assertEquals(firstName, childDto.getFirstName());
        assertEquals(lastName, childDto.getLastName());
        assertEquals(urlService.calculateAgePerson(birthdate), childDto.getAge());


    }

    @Test
    void testGetPhoneByStation() {
        // Mock data
        List<Firestation> firestations = Arrays.asList(
                new Firestation("123 Main St", "1"),
                new Firestation("456 Oak St", "2"),
                new Firestation("789 Pine St", "1")
        );

        List<Person> people = Arrays.asList(
                new Person("John", "Doe", "123 Main St", "City1", "12345", "123-456-7890", "john.doe@email.com"),
                new Person("Jane", "Smith", "456 Oak St", "City2", "67890", "987-654-3210", "jane.smith@email.com"),
                new Person("Bob", "Johnson", "789 Pine St", "City3", "54321", "111-222-3333", "bob.johnson@email.com")
        );

        // Mock repository calls
        when(firestationRepository.findAllFirestation()).thenReturn(firestations);
        when(personRepository.findAllPerson()).thenReturn(people);

        // Test the service method
        List<String> result = urlService.getPhoneByStation("1");

        // Verify the results
        assertEquals(2, result.size());
        assertEquals("123-456-7890", result.get(0));
        assertEquals("111-222-3333", result.get(1));

        // Verify repository method calls
        verify(firestationRepository, times(1)).findAllFirestation();
        verify(personRepository, times(1)).findAllPerson();
    }
    @Test
    void testGetPersonByAddress() {
        // Mock data
        String address = "123 Main St";

        List<Person> people = Arrays.asList(
                new Person("John", "Doe", "123 Main St", "City1", "12345", "123-456-7890", "john.doe@email.com"),
                new Person("Jane", "Smith", "456 Oak St", "City2", "67890", "987-654-3210", "jane.smith@email.com")
                // Add more people as needed
        );

        List<Firestation> firestations = Arrays.asList(
                new Firestation("123 Main St", "1"),
                new Firestation("456 Oak St", "2")
                // Add more firestations as needed
        );

        List<MedicalRecord> medicalRecords = Arrays.asList(
                new MedicalRecord("John", "Doe", "03/06/1980", Arrays.asList("Medication1"), Arrays.asList("Allergy1")),
                new MedicalRecord("Jane", "Smith", "05/12/1990", Arrays.asList("Medication2"), Arrays.asList("Allergy2"))
                // Add more medical records as needed
        );

        // Mock repository calls
        when(personRepository.findAllPerson()).thenReturn(people);
        when(firestationRepository.findAllFirestation()).thenReturn(firestations);
        when(medicalRecordRepository.findAllMedicalRecord()).thenReturn(medicalRecords);

        // Test the service method
        List<PersonAtAddressDto> result = urlService.getPersonByAddress(address);

        // Verify the results
        assertEquals(1, result.size());
        PersonAtAddressDto personAtAddressDto = result.get(0);
        assertEquals("John", personAtAddressDto.getFirstName());
        assertEquals("Doe", personAtAddressDto.getLastName());
        assertEquals("123-456-7890", personAtAddressDto.getPhone());
        assertEquals("1", personAtAddressDto.getStation());
        // Add more assertions as needed

        // Verify repository method calls
        verify(personRepository, times(1)).findAllPerson();
        verify(firestationRepository, times(1)).findAllFirestation();
        verify(medicalRecordRepository, times(1)).findAllMedicalRecord();
    }

    @Test
    void testGetPersonByStation() {
        // Mock data
        String station = "1";

        List<Firestation> firestations = Arrays.asList(
                new Firestation("123 Main St", "1"),
                new Firestation("456 Oak St", "2"),
                new Firestation("789 Pine St", "1")
        );

        List<Person> people = Arrays.asList(
                new Person("John", "Doe", "123 Main St", "City1", "12345", "123-456-7890", "john.doe@email.com"),
                new Person("Jane", "Smith", "456 Oak St", "City2", "67890", "987-654-3210", "jane.smith@email.com")
                // Add more people as needed
        );

        List<MedicalRecord> medicalRecords = Arrays.asList(
                new MedicalRecord("John", "Doe", "03/06/1980", Arrays.asList("Medication1"), Arrays.asList("Allergy1")),
                new MedicalRecord("Jane", "Smith", "05/12/1990", Arrays.asList("Medication2"), Arrays.asList("Allergy2"))
                // Add more medical records as needed
        );

        // Mock repository calls
        when(firestationRepository.findAllFirestation()).thenReturn(firestations);
        when(personRepository.findAllPerson()).thenReturn(people);
        when(medicalRecordRepository.findAllMedicalRecord()).thenReturn(medicalRecords);

        // Test the service method
        List<FloodDto> result = urlService.getPersonByStation(station);

        // Verify the results
        assertEquals(1, result.size());

        // Verify repository method calls
        verify(firestationRepository, times(1)).findAllFirestation();
        verify(personRepository, times(1)).findAllPerson();
        verify(medicalRecordRepository, times(1)).findAllMedicalRecord();
    }
    @Test
    void testGetPersonInfo() {
        // Mock data
        String firstName = "John";
        String lastName = "Doe";

        List<Person> people = Arrays.asList(
                new Person("John", "Doe", "123 Main St", "City1", "12345","841-874-6512", "john.doe@email.com"),
                new Person("Jane", "Smith", "456 Oak St", "City2", "67890","841-874-6512", "jane.smith@email.com")
                // Add more people as needed
        );

        List<MedicalRecord> medicalRecords = Arrays.asList(
                new MedicalRecord("John", "Doe", "03/06/1980", Arrays.asList("Medication1"), Arrays.asList("Allergy1")),
                new MedicalRecord("Jane", "Smith", "05/12/1990", Arrays.asList("Medication2"), Arrays.asList("Allergy2"))
                // Add more medical records as needed
        );

        // Mock repository calls
        when(personRepository.findAllPerson()).thenReturn(people);
        when(medicalRecordRepository.findAllMedicalRecord()).thenReturn(medicalRecords);

        // Test the service method
        List<PersonInfoDto> result = urlService.getPersonInfo(firstName, lastName);

        // Verify the results
        assertEquals(1, result.size());
        PersonInfoDto personInfoDto = result.get(0);
        assertEquals("John", personInfoDto.getFirstName());
        assertEquals("Doe", personInfoDto.getLastName());
        assertEquals("123 Main St", personInfoDto.getAddress());
        assertEquals("john.doe@email.com", personInfoDto.getEmail());
        // Add more assertions as needed

        // Verify repository method calls
        verify(personRepository, times(1)).findAllPerson();
        verify(medicalRecordRepository, times(1)).findAllMedicalRecord();
    }
    @Test
    void testGetEmailsByCity() {
        // Mock data
        String city = "City1";

        List<Person> people = Arrays.asList(
                new Person("John", "Doe", "123 Main St", "City1", "12345", "841-874-6512","john.doe@email.com"),
                new Person("Jane", "Smith", "456 Oak St", "City2", "67890","841-874-6512", "jane.smith@email.com"),
                new Person("Bob", "Johnson", "789 Pine St", "City1", "54321","841-874-6512", "bob.johnson@email.com")
                // Add more people as needed
        );

        // Mock repository call
        when(personRepository.findAllPerson()).thenReturn(people);

        // Test the service method
        ArrayList<Object> result = urlService.getEmailsByCity(city);

        // Verify the results
        assertEquals(2, result.size());
        assertEquals("john.doe@email.com", result.get(0));
        assertEquals("bob.johnson@email.com", result.get(1));
        // Add more assertions as needed

        // Verify repository method call
        verify(personRepository, times(1)).findAllPerson();
    }
}


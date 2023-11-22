package com.project.SafetyNet.service;

import com.project.SafetyNet.controller.dto.*;
import com.project.SafetyNet.model.Firestation;
import com.project.SafetyNet.model.MedicalRecord;
import com.project.SafetyNet.model.Person;
import com.project.SafetyNet.repository.FirestationRepository;
import com.project.SafetyNet.repository.MedicalRecordRepository;
import com.project.SafetyNet.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class URLServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private FirestationRepository firestationRepository;

    @Mock
    private FirestationService firestationService;

    @Mock
    private PersonService personService;

    @Mock
    private MedicalRecordService medicalRecordService;

    @InjectMocks
    private URLService urlService;
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
//    @Test
//    void testGetChildByAddress() {
//        // Mock data
//        String address = "1509 Culver St";
//
////        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
////        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984",
////                Arrays.asList("aznol:350mg", "hydrapermazol:100mg"),
////                Arrays.asList("illisoxian"));
////
////        List<Person> people = Arrays.asList(person);
////        List<MedicalRecord> medicalRecords = Arrays.asList(medicalRecord);
//
//        // Mock repository calls
//        when(personRepository.findAllPerson()).thenReturn(persons);
//        when(medicalRecordRepository.findAllMedicalRecord()).thenReturn(medicalRecords);
//        when(personService.getFamilyMembers("John", "Boyd")).thenReturn(Arrays.asList("FamilyMember1", "FamilyMember2"));
//
//        // Test the service method
//        List<ChildDto> result = urlService.getChildByAddress(address);
//
//        // Verify the results
//        assertEquals(1, result.size());
//        ChildDto childDto = result.get(0); // Utiliser l'index 0 car les indices commencent à 0
//        assertEquals("John", childDto.getFirstName());
//        assertEquals("Boyd", childDto.getLastName());
//        assertEquals(urlService.calculateAgePerson("03/06/1984"), childDto.getAge());
//        assertEquals(Arrays.asList("FamilyMember1", "FamilyMember2"), childDto.getFamily());
//        // Vérification que les méthodes mockées ont été appelées correctement
//        verify(personRepository, times(1)).findAllPerson();
//        verify(medicalRecordRepository, times(1)).findAllMedicalRecord();
//        verify(personService, times(1)).getFamilyMembers("John", "Boyd");
//
//
//    }


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


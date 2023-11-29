package com.project.SafetyNet.service;

import com.project.SafetyNet.exception.RessourceNotFoundException;
import com.project.SafetyNet.repository.FirestationRepositoryImpl;
import com.project.SafetyNet.repository.MedicalRecordRepositoryImpl;
import com.project.SafetyNet.repository.PersonRepository;
import com.project.SafetyNet.repository.PersonRepositoryImpl;
import com.project.SafetyNet.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MedicalRecordRepositoryImpl medicalRecordRepository;
    @Autowired
    private FirestationRepositoryImpl firestationRepository;
    @Autowired
    private FirestationServiceImpl firestationService;
    @Autowired
    private MedicalRecordServiceImpl medicalRecordService;
    @Autowired
    static final Logger logger = LogManager.getLogger(PersonService.class);

    public PersonServiceImpl(PersonRepositoryImpl personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAllPerson();
    }
    public Person addPerson(Person person) throws RessourceNotFoundException {
        if(person !=null) {
            personRepository.addPerson(person);

        }else{
            logger.error("person is empty");
            throw new RessourceNotFoundException("the person don't exist");

        }
        return person;
    }
    public Person updatePerson( String firstName, String lastName,Person personToUpdate) throws RessourceNotFoundException {
        Person result;
        if (firstName != null && lastName != null) {
            result = personRepository.updatePerson(firstName, lastName, personToUpdate);
        } else {
            logger.error("the" + personToUpdate + "don't exist");
            throw new RessourceNotFoundException("the person to update don't exist");
        }
        return result;
    }

    public void deletePerson(String firstName, String lastName)   {
        if(firstName !=null && lastName !=null) {
            personRepository.deletePerson(firstName, lastName);
        }else
            logger.error("the person don't exist");

    }


    public List<String> getFamilyMembers(String firstName, String lastName) {
        int i = 0;
        List<Person> listPersons = personRepository.findAllPerson();
        List<String> family = new ArrayList<>();
        while (i < listPersons.size()) {
            if (listPersons.get(i).getLastName().equals(lastName) && !listPersons.get(i).getFirstName().equals(firstName)) {
                family.add(listPersons.get(i).getFirstName());
//				listPersons.get(i).setFamilyMembers(family);
            }
            i++;
        }
        return family;
    }


}

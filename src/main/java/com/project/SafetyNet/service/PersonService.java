package com.project.SafetyNet.service;

import com.project.SafetyNet.exception.RessourceNotFoundException;
import com.project.SafetyNet.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();
    Person addPerson(Person person)throws RessourceNotFoundException;
    Person updatePerson( String firstName, String lastName,Person personToUpdate) throws RessourceNotFoundException;
    void deletePerson(String firstName, String lastName);
    List<String> getFamilyMembers(String firstName, String lastName);
}

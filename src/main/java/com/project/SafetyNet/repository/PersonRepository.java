package com.project.SafetyNet.repository;

import com.project.SafetyNet.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> findAllPerson();
    Person addPerson(Person person);
    Person updatePerson(String firstName, String lastName, Person personToUpdate);
    void deletePerson(String firstName, String lastName);
}

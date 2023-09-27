package com.project.SafetyNet.Service;

import com.project.SafetyNet.Repository.PersonRepository;
import com.project.SafetyNet.model.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {

        return personRepository.findAllPerson();
    }

    public Person addPerson(Person person) {
        personRepository.addPerson(person);
        return person;
    }



    public Person updatePerson(Person personToUpdate) {
        return personRepository.updatePerson(personToUpdate);
    }

    public Person deletePerson(Person person) {
        return personRepository.deletePerson(person);
    }

}
package com.project.SafetyNet.repository;
import com.project.SafetyNet.exception.RessourceNotFoundException;
import com.project.SafetyNet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class PersonRepository {
    @Autowired
    private DataRepository dataRepository;


    public List<Person> findAllPerson() {
        return this.dataRepository.getData().getPersons();
    }

    public Person addPerson(Person person) {
        this.dataRepository.getData().getPersons().add(person);
        return person;

    }

    public Person updatePerson(String firstName, String lastName, Person personToUpdate) {
        for (Person p : this.dataRepository.getData().getPersons()) {
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
                p.setAddress(personToUpdate.getAddress());
                p.setCity(personToUpdate.getCity());
                p.setZip(personToUpdate.getZip());
                p.setPhone(personToUpdate.getPhone());
                p.setEmail(personToUpdate.getEmail());
            }
        }
        return personToUpdate;
    }
    public void deletePerson(String firstName, String lastName) {
        Person personToDelete = null;
        for (Person p : this.dataRepository.getData().getPersons()) {
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
                personToDelete = p;
            }
        }
        this.dataRepository.getData().getPersons().remove(personToDelete);
    }


//    public  Person findFirstNameLastName(String firstName, String lastName){
//        Person personToFind = null;
//        for (Person p : this.dataRepository.getData().getPersons()) {
//            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
//                personToFind = p;
//            }
//        }
//        return personToFind;
//    }

}

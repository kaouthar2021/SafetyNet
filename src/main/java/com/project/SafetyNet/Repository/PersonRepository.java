package com.project.SafetyNet.Repository;
import com.project.SafetyNet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;



@Repository
public class PersonRepository {
    @Autowired
    private DataRepository dataRepository;

    private List<Person> personList = new ArrayList<Person>();

    public List<Person> findAllPerson() {
        return this.dataRepository.getData().getPersons();
    }

    public Person addPerson(Person person) {
        personList.add(person);
        return person;

    }

    public Person updatePerson(Person personToUpdate) {
        for (Person p : personList) {
            if (p.getFirstName().equals(personToUpdate.getFirstName()) && p.getLastName().equals(personToUpdate.getLastName()))
            {
                p.setAddress(personToUpdate.getAddress());
                p.setCity(personToUpdate.getCity());
                p.setZip(personToUpdate.getZip());
                p.setPhone(personToUpdate.getPhone());
                p.setEmail(personToUpdate.getEmail());
            }
        }
        return personToUpdate;
    }



    public Person deletePerson(Person person) {
        for (Person p : personList) {
            if (p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
                personList.remove(p);
            }
        }
        return person;
    }


}

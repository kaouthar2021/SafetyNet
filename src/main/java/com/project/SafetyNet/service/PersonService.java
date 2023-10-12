package com.project.SafetyNet.service;
import com.project.SafetyNet.controller.dto.CoveredPeople;
import com.project.SafetyNet.controller.dto.PersonSummray;
import com.project.SafetyNet.exception.RessourceNotFoundException;
import com.project.SafetyNet.model.Firestation;
import com.project.SafetyNet.repository.FirestationRepository;
import com.project.SafetyNet.repository.MedicalRecordRepository;
import com.project.SafetyNet.repository.PersonRepository;
import com.project.SafetyNet.model.Person;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private FirestationRepository firestationRepository;
    @Autowired
    static final Logger logger = LogManager.getLogger(PersonService.class);


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

    public void deletePerson(String firstName, String lastName)  throws RessourceNotFoundException {
        if(firstName !=null && lastName !=null) {
            personRepository.deletePerson(firstName, lastName);
        }else
            logger.error("the person don't exist");
        throw new RessourceNotFoundException("the person to delete don't exist");


    }



    public ArrayList<Object> getEmailsByCity(String city) {

        ArrayList<Object> listEmailsByCity = new ArrayList<Object>();
        List<Person> listPersons = personRepository.findAllPerson();
        for (Person person : listPersons) {
            if (person.getCity().equals(city)) {
                listEmailsByCity.add(person.getEmail());
            }
        }
        return listEmailsByCity;
    }

    public CoveredPeople  getPeopleCoveredByFirestation(String station){
        CoveredPeople coveredPeople=new CoveredPeople();
       List<Firestation>firestationList= firestationRepository.findAllFirestation();
       List<String>address=new ArrayList<>();
       for(Firestation f:firestationList){
           if(f.getStation().equals(station)){
               address.add(f.getAddress());
           }
       }
       coveredPeople.setPeople(new ArrayList<>());
       for (Person p:personRepository.findAllPerson()){
           if(address.contains(p.getAddress())){
               PersonSummray summray=new PersonSummray();
               summray.setFirstName(p.getFirstName());

               coveredPeople.getPeople().add(summray);
           }
       }

    }

}





















//    public CoveredPeople  getPeopleCoveredByFirestation(String station){
//        CoveredPeople coveredPeople=new CoveredPeople();
//        List<Firestation>firestationList= firestationRepository.findAllFirestation();
//        List<String>address=new ArrayList<>();
//        for(Firestation f:firestationList){
//            if(f.getStation().equals(station)){
//                address.add(f.getAddress());
//            }
//        }
//        coveredPeople.setPeople(new ArrayList<>());
//        for (Person p:personRepository.findAllPerson()){
//            if(address.contains(p.getAddress())){
//                PersonSummray summray=new PersonSummray();
//                summray.setFirstName(p.getFirstName());
//
//                coveredPeople.getPeople().add(summray);
//            }
//        }
//
//    }

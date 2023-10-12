package com.project.SafetyNet.controller;


import com.project.SafetyNet.exception.RessourceNotFoundException;
import com.project.SafetyNet.service.PersonService;
import com.project.SafetyNet.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("SafetyNet/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @GetMapping()
    public List<Person> getAllPersons() {

        return personService.getAllPersons();
    }

    @ResponseBody
    @PostMapping()
    public ResponseEntity<Person> addPerson(@RequestBody Person person) throws RessourceNotFoundException {

        personService.addPerson(person);
        logger.info("POST /person called");
        return new ResponseEntity<>(person, HttpStatus.CREATED);

    }


    @PutMapping()
    public ResponseEntity<Person> updatePerson( @RequestParam String  firstName , @RequestParam String lastName ,@RequestBody Person person) throws RessourceNotFoundException {
        logger.info("updating person {}", person);
        Person personUpdated = personService.updatePerson(firstName,lastName,person);
        return new ResponseEntity<Person>(personUpdated, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletePerson(@RequestParam String  firstName , @RequestParam String lastName)  throws RessourceNotFoundException{
        logger.info("deleting person {} {}", firstName,lastName);
         personService.deletePerson(firstName,lastName);
         return new ResponseEntity<>( HttpStatus.GONE);
    }

    @GetMapping("/communityEmail")
    public ArrayList<Object> getEmailsByCity(@RequestParam(defaultValue = "Culver") String city) {
        logger.info("getting emails of {}", city);
        return personService.getEmailsByCity(city);
    }
}





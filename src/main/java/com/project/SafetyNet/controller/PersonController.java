package com.project.SafetyNet.controller;

import com.project.SafetyNet.Service.PersonService;
import com.project.SafetyNet.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("SafetyNet")
public class PersonController {

    @Autowired
    private PersonService personService;

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("/persons")
    public List<Person> getAllPersons() {

        return personService.getAllPersons();
    }

    @ResponseBody
    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {

        personService.addPerson(person);
        logger.info("POST /person called");
        return new ResponseEntity<>(person, HttpStatus.CREATED);

    }


    @PutMapping("/person")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        logger.info("updating person {}", person);
        Person personUpdated = personService.updatePerson(person);
        return new ResponseEntity<Person>(personUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/person")
    public ResponseEntity<Person> deletePerson(Person person) {
        logger.info("deleting person {} {}", person);
        Person personDeleted = personService.deletePerson(person);
        return new ResponseEntity<Person>(personDeleted, HttpStatus.GONE);
    }


}





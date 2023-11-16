package com.project.SafetyNet.repository;

import com.project.SafetyNet.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class PersonRepositoryTest {
    @Autowired
    DataRepository dataRepository;
    @Autowired
    PersonRepository personRepository;
  private static List<Person> persons = new ArrayList<>();
    private Person john;
    private Person jacob;
    private Person tenley;

    @BeforeEach
    private  void setUp() throws IOException {
        john = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com");
        jacob = new Person(
                "Jacob",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6513",
                "drk@email.com");

        tenley = new Person(
                "Tenley",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "tenz@email.com");

        personRepository=new PersonRepository();

    }
    @Test
    public void findAllPersonTest() throws Exception{
        List<Person>personsTest=personRepository.findAllPerson();
        //assertThat(personsTest).isEqualTo(persons);
        Assertions.assertEquals(3, personsTest.size());
    }

    @Test
    private void addPersonTest() throws  Exception{
        Person personsTest = new Person("Silvio", "REA", "Test address", "city", "zip", "phone", "email");
        personRepository.addPerson(personsTest);
        persons.add(personsTest);
        assertThat(persons).contains(personsTest);

    }
}

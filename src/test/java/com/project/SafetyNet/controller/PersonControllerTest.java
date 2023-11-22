package com.project.SafetyNet.controller;

import com.jsoniter.output.JsonStream;
import com.project.SafetyNet.exception.RessourceNotFoundException;
import com.project.SafetyNet.model.Person;
import com.project.SafetyNet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private PersonService personService;

    @InjectMocks
    private PersonController personController;


    @Test
    void getAllPersonsTest() throws Exception {
        Person person1 = new Person("Toto", "Tutu", "1509 Culver St", "Culver", "97451", "841-874-6512",
                "toto@gmail.com");
        Person person2 = new Person("Felicia", "Cooper", "112 Steppes Pl", "Ghulja", "97451", "841-874-6874",
                "memet99@gmail.com");
        List<Person> personList = new ArrayList<>(Arrays.asList(person1, person2));

        when(personService.getAllPersons()).thenReturn(personList);
        mockMvc.perform(get("/SafetyNet/persons")).
                andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].firstName", is("Toto")));
        verify(personService).getAllPersons();
    }

    @Test
    void addPersonTest() throws Exception {
        Person personTest = new Person("Toto", "Tutu", "1509 Culver St", "Culver", "97451", "841-874-6512","toto@gmail.com");

        when(personService.addPerson(personTest)).thenReturn(personTest);

       mockMvc.perform(post("/SafetyNet/persons").content(JsonStream.serialize(personTest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

    }

    @Test
    void updatePersonTest() throws Exception {
        Person personTest1 = new Person("Toto", "Tutu", "1509 Culver St", "Culver", "97451", "841-874-6512", "toto@gmail.com");


        when(personService.updatePerson("Toto", "Tutu", personTest1)).thenReturn(personTest1);

        mockMvc.perform(MockMvcRequestBuilders.put("/SafetyNet/persons")
                        .param("firstName", "Toto")
                        .param("lastName", "Tutu")
                        .content(JsonStream.serialize(personTest1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(personService, times(1)).updatePerson("Toto", "Tutu", personTest1);
    }
    @Test
    void deletePersonTest() throws Exception {
        Person personTest = new Person("Toto", "Tutu", "1509 Culver St", "Culver", "97451", "841-874-6512",
                "toto@gmail.com");
       doNothing().when(personService).deletePerson("Toto", "Tutu");
        mockMvc.perform(MockMvcRequestBuilders.delete("/SafetyNet/persons")
                        .param("firstName", "Toto")
                        .param("lastName", "Tutu")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isGone());
        verify(personService, times(1)).deletePerson("Toto", "Tutu");

    }

    @Test
    void getFamilyMembersTest() {

        String firstName = "John";
        String lastName = "Boyd";
        List<String> familyMembers = Arrays.asList("FamilyMember1", "FamilyMember2");
        when(personService.getFamilyMembers(eq(firstName), eq(lastName))).thenReturn(familyMembers);

        List<String> result = personController.getFamilyMembers(firstName, lastName);

        assertEquals(familyMembers, result);
    }
}

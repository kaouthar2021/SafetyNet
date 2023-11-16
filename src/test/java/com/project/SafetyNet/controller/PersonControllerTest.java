package com.project.SafetyNet.controller;
//
//import com.jsoniter.JsonIterator;
//import com.jsoniter.output.JsonStream;
//import com.project.SafetyNet.model.Person;
//import com.project.SafetyNet.repository.DataRepository;
//import com.project.SafetyNet.repository.PersonRepository;
//import com.project.SafetyNet.service.PersonService;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@SpringBootTest
//@AutoConfigureMockMvc
//
//public class PersonControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    DataRepository dataRepository;
//    @Autowired
//    PersonRepository personRepository;
//
//    private Person personTest;
//    @BeforeEach
//    void setup(){
//       personTest = new Person("Toto", "Tutu", "1509 Culver St", "Culver", "97451", "841-874-6512", "toto@gmail.com");
//
//    }
//    @Test
//    public void getAllPersonsTest() throws Exception {
//        mockMvc.perform(get("/SafetyNet/persons"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].lastName", is("Boyd")));
//    }
//
//    @Test
//    public void addPersonTest() throws Exception {
//        mockMvc.perform(post("/SafetyNet/persons")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(personTest.toString()))
//                       .andExpect(status().isOk()).andReturn();
//        Person result=personRepository.findFirstNameLastName("Toto","Tutu");
//        Assertions.assertThat(result).isNotNull();
//    }
//    @Test
//    public void updatePersonTest() throws Exception {
//        mockMvc.perform(put("/SafetyNet/persons")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(personTest.toString()))
//                .andExpect(status().isOk());
//    }
//    @Test
//    public void deletePersonTest() throws Exception {
//        mockMvc.perform( delete("/SafetyNet/persons")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(personTest.toString()))
//                .andExpect(status().isOk()).andReturn();
//    }
//}

import com.project.SafetyNet.controller.PersonController;
import com.project.SafetyNet.exception.RessourceNotFoundException;
import com.project.SafetyNet.model.Person;
import com.project.SafetyNet.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void getAllPersons() throws Exception {
        List<Person> personList = new ArrayList<>();
        when(personService.getAllPersons()).thenReturn(personList);

        mockMvc.perform(get("/SafetyNet/persons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

    }

    @Test
    public void addPerson() throws Exception {
        Person person = new Person();
        when(personService.addPerson(person)).thenReturn(person);

        mockMvc.perform(post("/SafetyNet/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").doesNotExist());

    }

    @Test
    public void updatePerson() throws Exception {
        Person person = new Person();
        when(personService.updatePerson(anyString(), anyString(), any())).thenReturn(person);

        mockMvc.perform(put("/SafetyNet/persons")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").doesNotExist());

    }

    @Test
    public void deletePerson() throws Exception {
         doNothing().when(personService).deletePerson(anyString(), anyString());

            mockMvc.perform(delete("/SafetyNet/persons")
                            .param("firstName", "John")
                            .param("lastName", "Doe"))
                    .andExpect(status().isGone());

        }


    @Test
    public void getFamilyMembers() throws Exception {
        List<String> familyMembers = new ArrayList<>();
        when(personService.getFamilyMembers(anyString(), anyString())).thenReturn(familyMembers);

        mockMvc.perform(get("/SafetyNet/persons/family")
                        .param("firstName", "John")
                        .param("lastName", "Doe"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}

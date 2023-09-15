package com.project.SafetyNet.controller;

import com.jsoniter.JsonIterator;
import com.project.SafetyNet.model.Data;
import com.project.SafetyNet.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("SafetyNet")
public class PersonController {
    @Value("classpath:data.json")
    private Resource dataFile;
    @GetMapping("/read")
    private String readDataFile() throws IOException {
        String data = new String(dataFile.getInputStream().readAllBytes());
        return data;
    }




    @GetMapping("/data")
     public List<Person> afficher() throws IOException {
        String data = new String(dataFile.getInputStream().readAllBytes());
        Data result= JsonIterator.deserialize(data,Data.class);
        return result.getPersons();


    }


}

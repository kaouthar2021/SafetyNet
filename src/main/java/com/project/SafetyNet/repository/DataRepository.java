package com.project.SafetyNet.repository;

import com.jsoniter.JsonIterator;
import com.project.SafetyNet.model.Data;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class DataRepository {
    private Data data;
    private Resource dataFile=  new ClassPathResource("data.json");

    public DataRepository() throws IOException {
        String dataString = new String(dataFile.getInputStream().readAllBytes());
        Data result = JsonIterator.deserialize(dataString, Data.class);
        this.data = result;
    }
    public Data getData(){
        return this.data;
    }
}


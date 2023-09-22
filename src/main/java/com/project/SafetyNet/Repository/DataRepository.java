package com.project.SafetyNet.Repository;

import com.jsoniter.JsonIterator;
import com.project.SafetyNet.model.Data;
import org.springframework.beans.factory.annotation.Value;
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


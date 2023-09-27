package com.project.SafetyNet.Repository;

import com.project.SafetyNet.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirestationRepository {

    @Autowired
    private DataRepository dataRepository;
    public List<Firestation> findAllFirestation(){
        return this.dataRepository.getData().getFirestations();
    }
}

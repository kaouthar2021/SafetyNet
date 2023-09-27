package com.project.SafetyNet.Service;

import com.project.SafetyNet.Repository.FirestationRepository;
import com.project.SafetyNet.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {
    @Autowired
    private FirestationRepository firestationRepository;
    public List<Firestation> getAllFirestation(){
        return firestationRepository.findAllFirestation();
    }
}

package com.project.SafetyNet.Service;

import com.project.SafetyNet.Repository.FirestationRepository;
import com.project.SafetyNet.model.Firestation;
import com.project.SafetyNet.model.Person;
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

    public Firestation addFirestation(Firestation firestation) {
        firestationRepository.addFirestation(firestation);
        return firestation;
    }

//    public Firestation updateFirestation( String address,Firestation firestationToUpdate) {
//
//        return FirestationRepository.updateFirestation(address,firestationToUpdate);
//    }

//    public void deleteFirestation(String address) {
//        FirestationRepository.deleteFirestation(address);
//    }


}

package com.project.SafetyNet.service;

import com.project.SafetyNet.repository.FirestationRepository;
import com.project.SafetyNet.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class FirestationService {
    @Autowired
    private FirestationRepository firestationRepository;
    private String address;

    public List<Firestation> getAllFirestation(){
        return firestationRepository.findAllFirestation();
    }

    public Firestation addFirestation(Firestation firestation) {
        firestationRepository.addFirestation(firestation);
        return firestation;
    }

    public Firestation updateFirestation( String address,Firestation firestationToUpdate) {

        return firestationRepository.updateFirestation(address,firestationToUpdate);

    }

    public void deleteFirestation(String address) {
        firestationRepository.deleteFirestation(address);
    }

     public List<Firestation> findByAddress(String station) {
        List<Firestation> FirestationList = new ArrayList<Firestation>();
        for (Firestation f : this.firestationRepository.findAllFirestation()) {
            if (f.getStation().equals(station)) {
                FirestationList.add(f);
            }
        }
        return FirestationList;

    }

}

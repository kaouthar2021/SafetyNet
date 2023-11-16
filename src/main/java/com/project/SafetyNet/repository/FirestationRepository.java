package com.project.SafetyNet.repository;
import com.project.SafetyNet.model.Firestation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class FirestationRepository {

    @Autowired
    private DataRepository dataRepository;
    static final Logger logger = LogManager.getLogger(FirestationRepository.class);
    private List<Firestation> FirestationList = new ArrayList<>();

    public List<Firestation> findAllFirestation() {
        return this.dataRepository.getData().getFirestations();
    }


    public Firestation addFirestation(Firestation firestation) {
        dataRepository.getData().getFirestations().add(firestation);
        return firestation;

    }

    public Firestation updateFirestation(String address, Firestation firestationToUpdate) {
        for (Firestation f : this.dataRepository.getData().getFirestations()) {
            if (f.getAddress().equals(address)) {
                f.setStation(firestationToUpdate.getStation());

            }
        }
        return firestationToUpdate;
    }

    public void deleteFirestation(String address) {
        Firestation firestationToDelete = null;

        for (Firestation f : this.dataRepository.getData().getFirestations()) {
            if (f.getAddress().equals(address)) {
                firestationToDelete = f;

            }
        }

        this.dataRepository.getData().getFirestations().remove(firestationToDelete);
    }


}
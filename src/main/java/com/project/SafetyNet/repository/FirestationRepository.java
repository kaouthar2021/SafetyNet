package com.project.SafetyNet.repository;

import com.project.SafetyNet.model.Firestation;

import java.util.ArrayList;
import java.util.List;

public interface FirestationRepository {
    List<Firestation> FirestationList = new ArrayList<>();
    List<Firestation> findAllFirestation();
    Firestation addFirestation(Firestation firestation);
    Firestation updateFirestation(String address, Firestation firestationToUpdate);
    void deleteFirestation(String address);
}

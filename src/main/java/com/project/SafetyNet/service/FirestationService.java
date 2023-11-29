package com.project.SafetyNet.service;

import com.project.SafetyNet.exception.RessourceNotFoundException;
import com.project.SafetyNet.model.Firestation;

import java.util.List;

public interface FirestationService {
    List<Firestation> getAllFirestation();
    Firestation addFirestation(Firestation firestation) throws RessourceNotFoundException;
    Firestation updateFirestation( String address,Firestation firestationToUpdate) throws RessourceNotFoundException;
    void deleteFirestation(String address);

}

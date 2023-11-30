package com.project.SafetyNet.service;

import com.project.SafetyNet.exception.RessourceNotFoundException;
import com.project.SafetyNet.repository.FirestationRepository;
import com.project.SafetyNet.repository.FirestationRepositoryImpl;
import com.project.SafetyNet.model.Firestation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationServiceImpl implements FirestationService {
    @Autowired
    private FirestationRepository firestationRepository;
    @Autowired
    static final Logger logger = LogManager.getLogger(FirestationServiceImpl.class);

    private String address;

    public FirestationServiceImpl(FirestationRepository firestationRepository) {
        this.firestationRepository = firestationRepository;
    }

    public List<Firestation> getAllFirestation(){
        return firestationRepository.findAllFirestation();
    }

    public Firestation addFirestation(Firestation firestation) throws RessourceNotFoundException {
        if(firestation!=null) {
            firestationRepository.addFirestation(firestation);
        }else {
            logger.error("firestation is empty");
            throw new RessourceNotFoundException("firestation don't exist");

        }
        return firestation;
    }

    public Firestation updateFirestation( String address,Firestation firestationToUpdate) throws RessourceNotFoundException {
        if(address!=null) {
            return firestationRepository.updateFirestation(address, firestationToUpdate);
        }else {
            logger.error("the" + firestationToUpdate + "don't exist");
            throw new RessourceNotFoundException("firestation to update don't exist");
        }
    }

    public void deleteFirestation(String address) {
        if(address!=null) {
            firestationRepository.deleteFirestation(address);
        }else{
            logger.error("firestation don't exist");
    }
    }



}

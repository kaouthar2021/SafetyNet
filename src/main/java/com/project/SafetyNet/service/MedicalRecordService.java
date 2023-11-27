package com.project.SafetyNet.service;

import com.project.SafetyNet.exception.RessourceNotFoundException;
import com.project.SafetyNet.repository.MedicalRecordRepository;
import com.project.SafetyNet.model.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    static final Logger logger = LogManager.getLogger(MedicalRecordService.class);

    public List<MedicalRecord> getAllMedicalRecord() {
        return medicalRecordRepository.findAllMedicalRecord();
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) throws RessourceNotFoundException {
        if(medicalRecord!=null){
            medicalRecordRepository.addMedicalRecord(medicalRecord);

        }else{
            logger.error("medicalRecord is empty");
            throw new RessourceNotFoundException("medicalRecord don't exist");

        }
        return medicalRecord;
    }

    public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecordToUpdate) throws RessourceNotFoundException {
        if(firstName != null && lastName != null) {
            return medicalRecordRepository.updateMedicalRecord(firstName, lastName, medicalRecordToUpdate);
        } else {
            logger.error("the" + medicalRecordToUpdate + "don't exist");
            throw new RessourceNotFoundException("medicalRecord to update don't exist");
        }
    }

    public void deleteMedicalRecord(String firstName, String lastName)  {
        if(firstName != null && lastName != null) {
            medicalRecordRepository.deleteMedicalRepository(firstName, lastName);
        }else
            logger.error("medicalRecord don't exist");
    }





}


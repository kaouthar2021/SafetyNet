package com.project.SafetyNet.Service;

import com.project.SafetyNet.Repository.MedicalRecordRepository;
import com.project.SafetyNet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    public List<MedicalRecord> getAllMedicalRecord(){
        return medicalRecordRepository.findAllMedicalRecord();
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.addMedicalRecord(medicalRecord);
        return medicalRecord;
    }

    public MedicalRecord updateMedicalRecord( String firstName, String lastName,MedicalRecord medicalRecordToUpdate) {
        return medicalRecordRepository.updateMedicalRecord(firstName, lastName, medicalRecordToUpdate);
    }

    public void deleteMedicalRecord(String firstName, String lastName) {
        medicalRecordRepository.deleteMedicalRepository(firstName, lastName);
    }
}


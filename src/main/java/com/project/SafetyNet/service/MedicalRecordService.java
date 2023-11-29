package com.project.SafetyNet.service;

import com.project.SafetyNet.exception.RessourceNotFoundException;
import com.project.SafetyNet.model.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {

    List<MedicalRecord> getAllMedicalRecord();
    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) throws RessourceNotFoundException;
    MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecordToUpdate) throws RessourceNotFoundException;
    void deleteMedicalRecord(String firstName, String lastName);
}

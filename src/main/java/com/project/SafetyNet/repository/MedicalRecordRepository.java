package com.project.SafetyNet.repository;

import com.project.SafetyNet.model.MedicalRecord;

import java.util.List;

public interface MedicalRecordRepository {
    List<MedicalRecord> findAllMedicalRecord();
    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);
    MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecordToUpdate);
    void deleteMedicalRepository(String firstName, String lastName);
}

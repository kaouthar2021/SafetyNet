package com.project.SafetyNet.service;

import com.project.SafetyNet.repository.MedicalRecordRepository;
import com.project.SafetyNet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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


    public List<Object> findByFirstLastName(String firstName, String lastName) {
        List<Object> MedicalRecordList = new ArrayList<Object>();
        for (MedicalRecord m : this.medicalRecordRepository.findAllMedicalRecord()) {
            if (m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
                MedicalRecordList.add(m.getBirthdate());

        }
        return MedicalRecordList;
    }
    public int calculateAgePerson(String birthdate){
        return medicalRecordRepository.CalculateAgePerson(birthdate);
    }
}


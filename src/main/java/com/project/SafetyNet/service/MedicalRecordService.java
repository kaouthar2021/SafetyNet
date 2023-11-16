package com.project.SafetyNet.service;

import com.project.SafetyNet.repository.MedicalRecordRepository;
import com.project.SafetyNet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> getAllMedicalRecord() {
        return medicalRecordRepository.findAllMedicalRecord();
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.addMedicalRecord(medicalRecord);
        return medicalRecord;
    }

    public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecordToUpdate) {
        return medicalRecordRepository.updateMedicalRecord(firstName, lastName, medicalRecordToUpdate);
    }

    public void deleteMedicalRecord(String firstName, String lastName) {
        medicalRecordRepository.deleteMedicalRepository(firstName, lastName);
    }


    public String findByFirstLastName(String firstName, String lastName) {
        String birthday = null;
        for (MedicalRecord m : this.medicalRecordRepository.findAllMedicalRecord()) {
            if (m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
                birthday = m.getBirthdate();

        }
        return birthday;
    }

    public MedicalRecord findByName(String firstName, String lastName) {
        int i = 0;
        MedicalRecord medicalRecord = new MedicalRecord();
        List<MedicalRecord> listMedicalRecords = medicalRecordRepository.findAllMedicalRecord();
        while (i < listMedicalRecords.size()) {
            medicalRecord = listMedicalRecords.get(i);
            if (medicalRecord.getFirstName() != null && medicalRecord.getFirstName().equals(firstName)
                    && medicalRecord.getLastName().equals(lastName)) {
                return medicalRecord;
            }
            i++;
        }
        return null;
    }
}


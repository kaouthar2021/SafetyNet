package com.project.SafetyNet.repository;

import com.project.SafetyNet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepository {
    @Autowired
    private DataRepository dataRepository;


    public List<MedicalRecord> findAllMedicalRecord() {
        return this.dataRepository.getData().getMedicalrecords();
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        dataRepository.getData().getMedicalrecords().add(medicalRecord);
        return medicalRecord;

    }

    public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecordToUpdate) {
        for (MedicalRecord m : this.dataRepository.getData().getMedicalrecords()) {
            if (m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)) {
                m.setBirthdate(medicalRecordToUpdate.getBirthdate());
                m.setMedications(medicalRecordToUpdate.getMedications());
                m.setAllergies(medicalRecordToUpdate.getAllergies());

            }
        }
        return medicalRecordToUpdate;
    }

    public void deleteMedicalRepository(String firstName, String lastName) {
        MedicalRecord medicalRecordToDelete = null;
        for (MedicalRecord m : this.dataRepository.getData().getMedicalrecords()) {
            if (m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)) {
                medicalRecordToDelete = m;
            }
        }
        this.dataRepository.getData().getMedicalrecords().remove(medicalRecordToDelete);
    }




}

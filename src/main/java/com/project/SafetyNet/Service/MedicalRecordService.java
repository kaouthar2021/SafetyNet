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
}

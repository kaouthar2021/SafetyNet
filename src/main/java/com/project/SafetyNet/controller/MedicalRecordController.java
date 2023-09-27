package com.project.SafetyNet.controller;

import com.project.SafetyNet.Service.MedicalRecordService;
import com.project.SafetyNet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("SafetyNet")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;
    @GetMapping("/medicalRecord")
    public List<MedicalRecord> getAllMedicalRecord(){
        return medicalRecordService.getAllMedicalRecord();
    }
}

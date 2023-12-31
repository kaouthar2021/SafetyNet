package com.project.SafetyNet.controller;

import com.project.SafetyNet.exception.RessourceNotFoundException;
import com.project.SafetyNet.service.MedicalRecordService;
import com.project.SafetyNet.service.MedicalRecordServiceImpl;
import com.project.SafetyNet.model.MedicalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("SafetyNet/medicalRecord")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;
    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    @GetMapping()
    public List<MedicalRecord> getAllMedicalRecord(){
        return medicalRecordService.getAllMedicalRecord();
    }

    @ResponseBody
    @PostMapping()
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws RessourceNotFoundException {

        medicalRecordService.addMedicalRecord(medicalRecord);
        logger.info("POST /medicalRecord called");
        return new ResponseEntity<>(medicalRecord, HttpStatus.CREATED);

    }

    @PutMapping()
    public ResponseEntity<MedicalRecord> updateMedicalRecord( @RequestParam String  firstName , @RequestParam String lastName ,@RequestBody MedicalRecord medicalRecord) throws RessourceNotFoundException {
        logger.info("updating medicalRecord {}", medicalRecord);
        MedicalRecord medicalRecordUpdated = medicalRecordService.updateMedicalRecord(firstName,lastName,medicalRecord);
        return new ResponseEntity<MedicalRecord>(medicalRecordUpdated, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteMedicalRecord(@RequestParam String  firstName , @RequestParam String lastName) throws RessourceNotFoundException {
        logger.info("deleting medicalRecord {} {}", firstName,lastName);
        medicalRecordService.deleteMedicalRecord(firstName,lastName);
        return new ResponseEntity<>( HttpStatus.GONE);
    }


}

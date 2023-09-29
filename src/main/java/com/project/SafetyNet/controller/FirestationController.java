package com.project.SafetyNet.controller;

import com.project.SafetyNet.Repository.FirestationRepository;
import com.project.SafetyNet.Service.FirestationService;
import com.project.SafetyNet.model.Firestation;
import com.project.SafetyNet.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("SafetyNet/firestation")
public class FirestationController {
     @Autowired
    private FirestationService firestationService;
    private static final Logger logger = LoggerFactory.getLogger(FirestationController.class);

     @GetMapping()
     public List<Firestation> getAllFirestations(){
         return firestationService.getAllFirestation();
     }

    @ResponseBody
    @PostMapping()
    public ResponseEntity<Firestation> addFirestation(@RequestBody Firestation firestation) {

        firestationService.addFirestation(firestation);
        logger.info("POST /firestation called");
        return new ResponseEntity<>(firestation, HttpStatus.CREATED);

    }
}

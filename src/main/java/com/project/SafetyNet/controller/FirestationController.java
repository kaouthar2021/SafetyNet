package com.project.SafetyNet.controller;

import com.project.SafetyNet.Repository.FirestationRepository;
import com.project.SafetyNet.Service.FirestationService;
import com.project.SafetyNet.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("SafetyNet")
public class FirestationController {
     @Autowired
    private FirestationService firestationService;
     @GetMapping("firestation")
     public List<Firestation> getAllFirestations(){
         return firestationService.getAllFirestation();
     }
}

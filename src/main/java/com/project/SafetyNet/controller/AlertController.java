package com.project.SafetyNet.controller;

import com.project.SafetyNet.controller.dto.PersonSummray;
import com.project.SafetyNet.model.Firestation;
import com.project.SafetyNet.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController

public class AlertController {

    @Autowired
    private FirestationService firestationService;


}

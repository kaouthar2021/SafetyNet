package com.project.SafetyNet.controller;

import com.project.SafetyNet.service.FirestationService;
import com.project.SafetyNet.model.Firestation;
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
    @PutMapping()
    public ResponseEntity<Firestation> updateFirestation(@RequestParam String  address , @RequestBody Firestation firestation)  {
        logger.info("updating firestation {}", firestation);
        Firestation updateFirestation = firestationService.updateFirestation(address,firestation);
        return new ResponseEntity<Firestation>(updateFirestation, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteFirestation(@RequestParam String  address)  {
        logger.info("deleting firestation {} ", address);
        firestationService.deleteFirestation(address);
        return new ResponseEntity<>( HttpStatus.GONE);
    }

//    @GetMapping("/fire")
//    public List<Firestation> findByAddress(@RequestParam String station){
//        logger.info("getting station of {}", station);
//      return   firestationService.findByAddress(station);
//     // return new ResponseEntity<>(HttpStatus.OK);
//    }

}

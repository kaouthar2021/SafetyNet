package com.project.SafetyNet.controller;
import com.project.SafetyNet.controller.dto.*;
import com.project.SafetyNet.service.FirestationService;
import com.project.SafetyNet.service.PersonService;
import com.project.SafetyNet.service.URLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("SafetyNet/url")
public class URLController {

    @Autowired
    private FirestationService firestationService;

    @Autowired
    private PersonService personService;
    @Autowired
    private URLService urlService;
    private static final Logger logger = LoggerFactory.getLogger(URLController.class);
    @GetMapping("/firestation")
    public CoveredPeople getPersonCoveredByFirestation(@RequestParam String station){
        logger.info("station number{}",station);
        return urlService.getPeopleCoveredByFirestation(station);
    }
    @GetMapping("/childAlert")
    public List<ChildDto> getChildByAdress(@RequestParam String address){
        logger.info("child by address");
        return  urlService.getChildByAddress(address);
    }
    @GetMapping("/phoneAlert")
    public List<String> getPhoneByStation(@RequestParam String station){
        logger.info("get phone by station number",station);
        return urlService.getPhoneByStation(station);
    }
   @GetMapping("/fire")
    public List<PersonAtAddressDto> getPersonByAddress(@RequestParam String address){
        logger.info("person by address");
        return urlService.getPersonByAddress(address);
   }
   @GetMapping("/flood")
        public List<FloodDto> getPersonByStation(@RequestParam String station){
        logger.info("person by station");
        return urlService.getPersonByStation(station);
   }
    @GetMapping("/personInfo")
    public List<PersonInfoDto> getPersonInfo(@RequestParam String firstName,String lastName){
        logger.info("info person");
        return urlService.getPersonInfo(firstName,lastName);
    }
    @GetMapping("/communityEmail")
    public ArrayList<Object> getEmailsByCity(@RequestParam(defaultValue = "Culver") String city) {
        logger.info("getting emails of {}", city);
        return urlService.getEmailsByCity(city);
    }

}

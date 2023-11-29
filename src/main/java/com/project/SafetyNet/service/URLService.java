package com.project.SafetyNet.service;

import com.project.SafetyNet.controller.dto.*;

import java.util.ArrayList;
import java.util.List;

public interface URLService {
    int calculateAgePerson(String birthdate);
    CoveredPeople getPeopleCoveredByFirestation(String station);
    List<ChildDto> getChildByAddress(String address);
    List<String> getPhoneByStation(String station);
    List<PersonAtAddressDto> getPersonByAddress(String address);
    List<FloodDto> getPersonByStation(String station);
    List<PersonInfoDto> getPersonInfo(String firstName, String lastName);
    ArrayList<Object> getEmailsByCity(String city);
}

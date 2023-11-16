package com.project.SafetyNet.controller.dto;

import lombok.Data;

import java.util.List;
@Data
public class CoveredPeople {
    private Integer numberChild;
    private  Integer numberAdult;
    private List<PersonSummray>  People;


}

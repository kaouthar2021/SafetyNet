package com.project.SafetyNet.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonHouseDto {
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;
}

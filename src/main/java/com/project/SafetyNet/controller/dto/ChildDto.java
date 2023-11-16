package com.project.SafetyNet.controller.dto;

import lombok.Data;

import java.util.List;
@Data
public class ChildDto {
    private String firstName;
    private String lastName;
    private int age;
    private List<String> Family;

}

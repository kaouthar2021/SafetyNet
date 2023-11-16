package com.project.SafetyNet.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonInfoDto {
   private String firstName;
   private String lastName;
   private  String address;
   private  String email;
   private int age;
   private   List<String> medications ;
    private List<String> allergies;

}

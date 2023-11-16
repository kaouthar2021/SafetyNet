package com.project.SafetyNet.controller.dto;

import lombok.Data;

import java.util.List;
@Data
public class FloodDto {
    private String address;
    private List<PersonHouseDto> personHouseDtos;
}

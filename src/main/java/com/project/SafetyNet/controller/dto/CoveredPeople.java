package com.project.SafetyNet.controller.dto;

import java.util.List;

public class CoveredPeople {
    private Integer numberChild;
    private  Integer numberAdult;
    private List<PersonSummray>  People;

    public Integer getNumberChild() {
        return numberChild;
    }

    public void setNumberChild(Integer numberChild) {
        this.numberChild = numberChild;
    }

    public Integer getNumberAdult() {
        return numberAdult;
    }

    public void setNumberAdult(Integer numberAdult) {
        this.numberAdult = numberAdult;
    }

    public List<PersonSummray> getPeople() {
        return People;
    }

    public void setPeople(List<PersonSummray> people) {
        People = people;
    }
}

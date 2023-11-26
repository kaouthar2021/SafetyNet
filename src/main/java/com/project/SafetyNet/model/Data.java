package com.project.SafetyNet.model;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Person> persons;
    private List<Firestation>firestations;
    private List<MedicalRecord> medicalrecords;

    public Data() {
        this.persons = new ArrayList<>();
        this.firestations = new ArrayList<>();
        this.medicalrecords = new ArrayList<>();
    }


    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Firestation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<Firestation> firestations) {
        this.firestations = firestations;
    }

    public List<MedicalRecord> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }


}

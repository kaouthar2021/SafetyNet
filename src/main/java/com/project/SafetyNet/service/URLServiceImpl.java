package com.project.SafetyNet.service;

import com.project.SafetyNet.controller.dto.*;
import com.project.SafetyNet.model.Firestation;
import com.project.SafetyNet.model.MedicalRecord;
import com.project.SafetyNet.model.Person;
import com.project.SafetyNet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class URLServiceImpl implements URLService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private FirestationRepository firestationRepository;
    @Autowired
    private FirestationService firestationService;
    @Autowired
    private PersonService personService;
    @Autowired
    private MedicalRecordService medicalRecordService;
    public int calculateAgePerson(String birthdate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate =LocalDate.parse(birthdate,df);
        LocalDate currentDate=LocalDate.now();
        return  Period.between(birthDate,currentDate).getYears();

    }
    public CoveredPeople  getPeopleCoveredByFirestation(String station){
        CoveredPeople coveredPeople=new CoveredPeople();
        List<Firestation>firestationList= firestationRepository.findAllFirestation();
        List<String>address=new ArrayList<>();
        for(Firestation f:firestationList){
            if(f.getStation().equals(station)){
                address.add(f.getAddress());
            }
        }
        coveredPeople.setPeople(new ArrayList<>());
        coveredPeople.setNumberAdult(0);
        coveredPeople.setNumberChild(0);
        for (Person p:personRepository.findAllPerson()) {
            if (address.contains(p.getAddress())) {
                PersonSummray summray = new PersonSummray();
                summray.setFirstName(p.getFirstName());
                summray.setLastName(p.getLastName());
                summray.setAddress(p.getAddress());
                summray.setPhone(p.getPhone());
                coveredPeople.getPeople().add(summray);
                for (MedicalRecord m : medicalRecordRepository.findAllMedicalRecord()) {
                    if (m.getFirstName().equals(summray.getFirstName())&&m.getLastName().equals(summray.getLastName())){
                        int age= this.calculateAgePerson(m.getBirthdate());
                        if(age<=18){
                            coveredPeople.setNumberChild(coveredPeople.getNumberChild()+1);
                        }else {
                            coveredPeople.setNumberAdult(coveredPeople.getNumberAdult()+1);
                        }
                    }
                }
            }
        }
        return coveredPeople;
    }
    public List<ChildDto> getChildByAddress(String address) {
        ArrayList<ChildDto> childDtList = new ArrayList<>();
        for (Person p : personRepository.findAllPerson()) {
            if (address.equals(p.getAddress())) {
                for (MedicalRecord m : medicalRecordRepository.findAllMedicalRecord()) {
                    if (m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())) {
                        int age = this.calculateAgePerson(m.getBirthdate());
                        if (age <= 18) {
                            ChildDto childDto = new ChildDto();
                            childDto.setFirstName(m.getFirstName());
                            childDto.setLastName(m.getLastName());
                            childDto.setAge(age);
                            childDto.setFamily(personService.getFamilyMembers(m.getFirstName(), m.getLastName()));
                            childDtList.add(childDto);
                        }
                    }
                }
            }
        }
        return childDtList;
    }
    public List<String> getPhoneByStation(String station) {
        List<String> address = new ArrayList<>();
        List<String> listPhoneNumber = new ArrayList<>();
        for (Firestation f : firestationRepository.findAllFirestation()) {
            if (station.equals(f.getStation())) {
                address.add(f.getAddress());
            }
        }
        for (Person p : personRepository.findAllPerson()) {
            if (address.contains(p.getAddress())) {
                listPhoneNumber.add(p.getPhone());
            }
        }
        return listPhoneNumber;
    }
    public List<PersonAtAddressDto> getPersonByAddress(String address) {
        List<PersonAtAddressDto> personAtAddressDtoList = new ArrayList<>();

        for (Person p : personRepository.findAllPerson()) {
            PersonAtAddressDto personAtAddressDto = new PersonAtAddressDto();
            if (p.getAddress().equals(address)) {
                personAtAddressDto.setFirstName(p.getFirstName());
                personAtAddressDto.setLastName(p.getLastName());
                personAtAddressDto.setPhone(p.getPhone());
                for (Firestation f : firestationRepository.findAllFirestation()) {
                    if (f.getAddress().equals(address)) {
                        personAtAddressDto.setStation(f.getStation());
                        for (MedicalRecord m : medicalRecordRepository.findAllMedicalRecord()) {
                            if (m.getFirstName().equals(personAtAddressDto.getFirstName()) && m.getLastName().equals(personAtAddressDto.getLastName())) {
                                personAtAddressDto.setAge(this.calculateAgePerson(m.getBirthdate()));
                                personAtAddressDto.setMedications(m.getMedications());
                                personAtAddressDto.setAllergies(m.getAllergies());
                            }
                        }
                        personAtAddressDtoList.add(personAtAddressDto);
                    }
                }
            }
        }
        return personAtAddressDtoList;
    }
    public List<FloodDto> getPersonByStation(String station) {
        List<FloodDto> floodDtoList = new ArrayList<>();
        List<String> address = new ArrayList<>();
        for (Firestation f : firestationRepository.findAllFirestation()) {
            if (f.getStation().equals(station)) {
                address.add(f.getAddress());
            }
        }

        for (Person p : personRepository.findAllPerson()) {
            if (address.contains(p.getAddress())) {
                FloodDto floodDto=new FloodDto();
                floodDto.setPersonHouseDtos(new ArrayList<>());
                PersonHouseDto personHouseDto=new PersonHouseDto();
                floodDto.setAddress(p.getAddress());
                personHouseDto.setFirstName(p.getFirstName());
                personHouseDto.setLastName(p.getLastName());
                personHouseDto.setPhone(p.getPhone());

                for (MedicalRecord m : medicalRecordRepository.findAllMedicalRecord()) {
                    if (m.getFirstName().equals(personHouseDto.getFirstName()) && (m.getLastName().equals(personHouseDto.getLastName()))) {
                        personHouseDto.setAge(this.calculateAgePerson(m.getBirthdate()));
                        personHouseDto.setMedications(m.getMedications());
                        personHouseDto.setAllergies(m.getAllergies());
                        floodDto.getPersonHouseDtos().add(personHouseDto);
                    }
                }
                floodDtoList.add(floodDto);

            }
        }
        return floodDtoList;
    }
    public List<PersonInfoDto> getPersonInfo(String firstName, String lastName) {
        ArrayList<PersonInfoDto> personInfoDtoList = new ArrayList<>();
        for (Person p : personRepository.findAllPerson()) {
            PersonInfoDto personInfoDto = new PersonInfoDto();
            if (p.getLastName().equals(lastName)) {
                personInfoDto.setFirstName(p.getFirstName());
                personInfoDto.setLastName(p.getLastName());
                personInfoDto.setAddress(p.getAddress());
                personInfoDto.setEmail(p.getEmail());
                for (MedicalRecord m : medicalRecordRepository.findAllMedicalRecord()) {
                    if (m.getLastName().equals(personInfoDto.getLastName())) {
                        personInfoDto.setAge(this.calculateAgePerson(m.getBirthdate()));
                        personInfoDto.setMedications(m.getMedications());
                        personInfoDto.setAllergies(m.getAllergies());
                    }
                }
                personInfoDtoList.add(personInfoDto);
            }
        }
        return personInfoDtoList;
    }
    public ArrayList<Object> getEmailsByCity(String city) {
        ArrayList<Object> listEmailsByCity = new ArrayList<Object>();
        List<Person> listPersons = personRepository.findAllPerson();
        for (Person person : listPersons) {
            if (person.getCity().equals(city)) {
                listEmailsByCity.add(person.getEmail());
            }
        }
        return listEmailsByCity;
    }
}
package com.project.SafetyNet.Repository;
import com.project.SafetyNet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class PersonRepository {
    @Autowired
    private DataRepository dataRepository;
    public List<Person> findAllPerson() {
        return this.dataRepository.getData().getPersons();
    }
}
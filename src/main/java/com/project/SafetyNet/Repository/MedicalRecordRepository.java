package com.project.SafetyNet.Repository;

import com.project.SafetyNet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MedicalRecordRepository {
    @Autowired
    private DataRepository dataRepository;

    public List<MedicalRecord> findAllMedicalRecord() {
        return this.dataRepository.getData().getMedicalrecords();
    }


}

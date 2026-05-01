package com.rehaflow.profile_service.infrastructure.grpc.index_search;

import com.rehaflow.profile_service.domain.patient_profile.PatientProfileEntity;
import com.rehaflow.profile_service.domain.rehab_request.RehabRequestEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class IndexSearchMapper {
    public Map<String, String> toMapDoctorPatient(PatientProfileEntity patientProfile) {
        Map<String, String> map = new HashMap<>();

        map.put("fullName", patientProfile.getFullName());
        map.put("birthdate", String.valueOf(patientProfile.getBirthday()));
        map.put("doctorId",  patientProfile.getDoctor().getId().toString());

        return map;
    }
}

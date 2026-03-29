package com.rehaflow.profile_service.domain.profile;

import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileRepository;
import com.rehaflow.profile_service.domain.hospital_profile.HospitalProfileRepository;
import com.rehaflow.profile_service.domain.patient_profile.PatientProfileEntity;
import com.rehaflow.profile_service.domain.patient_profile.PatientProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.rehaflow.profile_service.grpc.AccountType;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final DoctorProfileRepository doctorProfileRepository;
    private final HospitalProfileRepository hospitalProfileRepository;
    private final PatientProfileRepository patientProfileRepository;

    public <T> JpaRepository<T, UUID> getRepo(AccountType profileType) {
        return switch (profileType) {
            case PATIENT -> (JpaRepository<T, UUID>) patientProfileRepository;
            case DOCTOR -> (JpaRepository<T, UUID>) doctorProfileRepository;
            case HOSPITAL -> (JpaRepository<T, UUID>) hospitalProfileRepository;
            default -> throw new IllegalArgumentException("Unknown profile type: " + profileType);
        };
    }
}

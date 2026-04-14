package com.rehaflow.profile_service.domain.dto.profile;

import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileEntity;
import com.rehaflow.profile_service.domain.hospital_profile.HospitalProfileEntity;
import com.rehaflow.profile_service.domain.patient_profile.PatientProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapperDto {

    // =========================
    // DOCTOR
    // =========================

    public DoctorProfileDto toDoctorDto(DoctorProfileEntity entity) {
        return DoctorProfileDto.builder()
                .fullName(entity.getFullName())
                .direction(entity.getSpecialization())
                .country(entity.getCountry())
                .city(entity.getCity())
                .hospitalName(entity.getHospitalName())
                .birthday(entity.getBirthday())
                .build();
    }

    // =========================
    // PATIENT
    // =========================

    public PatientProfileDto toPatientDto(PatientProfileEntity entity) {
        return PatientProfileDto.builder()
                .fullName(entity.getFullName())
                .height(entity.getHeight())
                .weight(entity.getWeight())
                .afterOperation(entity.getAfterOperation())
                .afterTrauma(entity.getAfterTrauma())
                .limitedMovement(entity.getLimitedMovement())
                .birthday(entity.getBirthday())
                .build();
    }

    // =========================
    // HOSPITAL
    // =========================

    public HospitalProfileDto toHospitalDto(HospitalProfileEntity entity) {
        return HospitalProfileDto.builder()
                .country(entity.getCountry())
                .city(entity.getCity())
                .address(entity.getAddress())
                .hospitalType(entity.getType())
                .hospitalName(entity.getHospitalName())
                .build();
    }
}
package com.rehaflow.profile_service.app.public_profile.doctor;

import com.rehaflow.profile_service.app.public_profile.doctor.dto.DoctorGraphDTO;
import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DoctorGraphMapper {

    public DoctorGraphDTO toDto(DoctorProfileEntity e) {
        return DoctorGraphDTO.builder()
                .id(e.getId().toString())
                .full_name(e.getFullName())
                .birthday(e.getBirthday().toString())
                .country(e.getCountry())
                .city(e.getCity())
                .specialization(e.getSpecialization())
                .hospital_name(e.getHospitalName())
                .hospital_profile(
                        e.getHospitalProfile() != null
                                ? e.getHospitalProfile().getId().toString()
                                : null
                )
                .protocols(e.getProtocols())
                .build();
    }
}
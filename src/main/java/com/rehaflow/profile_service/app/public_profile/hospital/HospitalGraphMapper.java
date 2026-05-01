package com.rehaflow.profile_service.app.public_profile.hospital;

import com.rehaflow.profile_service.app.public_profile.doctor.dto.DoctorGraphDTO;
import com.rehaflow.profile_service.app.public_profile.hospital.dto.HospitalGraphDTO;
import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileEntity;
import com.rehaflow.profile_service.domain.hospital_profile.HospitalProfileEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HospitalGraphMapper {
    public HospitalGraphDTO toDto(HospitalProfileEntity e, List<DoctorGraphDTO> doctors) {
        return HospitalGraphDTO.builder()
                .id(e.getId().toString())
                .name(e.getHospitalName())
                .country(e.getCountry())
                .city(e.getCity())
                .address(e.getAddress())
                .type(e.getType())
                .doctors(doctors)
                .build();
    }
}

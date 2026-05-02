package com.rehaflow.profile_service.app.doctor.doctor_dashboard;

import com.rehaflow.profile_service.app.doctor.doctor_dashboard.dto.DoctorDashboardDTO.PrivateDoctorDto;
import com.rehaflow.profile_service.app.doctor.doctor_dashboard.dto.DoctorDashboardDTO.RehabRequestDto;
import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileEntity;
import com.rehaflow.profile_service.domain.rehab_request.RehabRequestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DoctorDtoMapper {
    private final RehabRequestMapper rehabRequestMapper;
    public PrivateDoctorDto toPrivateDto(DoctorProfileEntity doctor,
                                         List<RehabRequestEntity> rehabRequests) {

        return PrivateDoctorDto.builder()
                .id(doctor.getId().toString())
                .full_name(doctor.getFullName())
                .birthday(doctor.getBirthday().toString())
                .country(doctor.getCountry())
                .city(doctor.getCity())
                .specialization(doctor.getSpecialization())
                .hospital_name(doctor.getHospitalName())
                .hospital_profile(
                        doctor.getHospitalProfile() != null
                                ? doctor.getHospitalProfile().getId().toString()
                                : null
                )
                .protocols(doctor.getProtocols())
                .rehabRequests(
                        rehabRequests.stream()
                                .map(rehabRequestMapper::toDto)
                                .toList()
                )
                .build();
    }
}

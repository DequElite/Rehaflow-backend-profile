package com.rehaflow.profile_service.app.public_profile.hospital;

import com.rehaflow.profile_service.app.public_profile.doctor.DoctorGraphMapper;
import com.rehaflow.profile_service.app.public_profile.doctor.dto.DoctorGraphDTO;
import com.rehaflow.profile_service.app.public_profile.hospital.dto.HospitalGraphDTO;
import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileRepository;
import com.rehaflow.profile_service.domain.hospital_profile.HospitalProfileEntity;
import com.rehaflow.profile_service.domain.hospital_profile.HospitalProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetHospitalByIdService {
    private final HospitalProfileRepository repository;
    private final DoctorProfileRepository doctorRepository;
    private final HospitalGraphMapper mapper;
    private final DoctorGraphMapper doctorMapper;

    public HospitalGraphDTO getHospitalById(String id) {
        UUID hospitalId = UUID.fromString(id);

        HospitalProfileEntity hospital = repository.findById(hospitalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<DoctorGraphDTO> doctors = doctorRepository
                .findAllByHospitalProfile_Id(hospitalId)
                .stream()
                .map(doctorMapper::toDto)
                .toList();

        return mapper.toDto(hospital, doctors);
    }
}
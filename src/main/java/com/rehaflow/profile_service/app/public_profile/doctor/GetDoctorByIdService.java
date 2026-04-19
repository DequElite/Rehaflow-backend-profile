package com.rehaflow.profile_service.app.public_profile.doctor;

import com.rehaflow.profile_service.app.public_profile.doctor.dto.DoctorGraphDTO;
import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileEntity;
import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetDoctorByIdService {
    private final DoctorProfileRepository repository;
    private final DoctorGraphMapper mapper;

    public DoctorGraphDTO getDoctorById(String id) {
        Optional<DoctorProfileEntity> doctorProfileEntity = this.repository.findById(UUID.fromString(id));
        if(doctorProfileEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return mapper.toDto(doctorProfileEntity.get());
    }
}

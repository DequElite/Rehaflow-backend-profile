package com.rehaflow.profile_service.app.api.get_profile;

import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileEntity;
import com.rehaflow.profile_service.domain.hospital_profile.HospitalProfileEntity;
import com.rehaflow.profile_service.domain.patient_profile.PatientProfileEntity;
import com.rehaflow.profile_service.domain.profile.ProfileEntity;
import com.rehaflow.profile_service.domain.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetProfileService {
    private final ProfileService profileService;

    public com.rehaflow.profile_service.grpc.GetProfileByIdResponse getProfileById(com.rehaflow.profile_service.grpc.GetProfileByIdRequest request) {
        JpaRepository<? extends ProfileEntity, UUID> repository = profileService.getRepo(request.getProfileType());

        Object profile = repository
                .findById(UUID.fromString(request.getProfileId()))
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        if(!(profile instanceof ProfileEntity profileEntity)) {
            throw new IllegalArgumentException("Unknown profile type: " + request.getProfileType());
        }

        if(!profileEntity.getUserId().equals(UUID.fromString(request.getUserId()))) {
            throw new IllegalArgumentException("Unknown user: " + request.getUserId());
        }

        var builder = com.rehaflow.profile_service.grpc.GetProfileByIdResponse.newBuilder()
                .setUserId(request.getUserId())
                .setProfileType(request.getProfileType());

        switch (request.getProfileType()) {

            case PATIENT -> {
                PatientProfileEntity patient = (PatientProfileEntity) profile;

                builder.setPatientProfile(
                        com.rehaflow.profile_service.grpc.PatientProfileDto.newBuilder()
                                .setFullName(patient.getFullName())
                                .setHeight(patient.getHeight())
                                .setWeight(patient.getWeight())
                                .setAfterTrauma(patient.getAfterTrauma())
                                .setAfterOperation(patient.getAfterOperation())
                                .setLimitedMovement(patient.getLimitedMovement())
                                .build()
                );
            }

            case DOCTOR -> {
                DoctorProfileEntity doctor = (DoctorProfileEntity) profile;

                builder.setDoctorProfile(
                        com.rehaflow.profile_service.grpc.DoctorProfileDto.newBuilder()
                                .setFullName(doctor.getFullName())
                                .setCountry(doctor.getCountry())
                                .setCity(doctor.getCity())
                                .setDirection(doctor.getSpecialization())
                                .setHospitalName(doctor.getHospitalName())
                                .build()
                );
            }

            case HOSPITAL -> {
                HospitalProfileEntity hospital = (HospitalProfileEntity) profile;

                builder.setHospitalProfile(
                        com.rehaflow.profile_service.grpc.HospitalProfileDto.newBuilder()
                                .setHospitalName(hospital.getHospitalName())
                                .setCountry(hospital.getCountry())
                                .setCity(hospital.getCity())
                                .setAddress(hospital.getAddress())
                                .setHospitalType(hospital.getType())
                                .build()
                );
            }

            default -> throw new IllegalArgumentException("Unknown profile type");
        }

        return builder.build();
    }
}

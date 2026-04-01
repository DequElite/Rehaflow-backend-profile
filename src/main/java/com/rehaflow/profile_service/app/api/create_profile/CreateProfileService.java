package com.rehaflow.profile_service.app.api.create_profile;

import com.google.protobuf.Timestamp;
import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileEntity;
import com.rehaflow.profile_service.domain.hospital_profile.HospitalProfileEntity;
import com.rehaflow.profile_service.domain.patient_profile.PatientProfileEntity;
import com.rehaflow.profile_service.domain.profile.ProfileEntity;
import com.rehaflow.profile_service.domain.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.rehaflow.profile_service.grpc.CreateProfileResponse;
import com.rehaflow.profile_service.grpc.CreateProfileDto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateProfileService {
    private final ProfileService profileService;

    private LocalDate convertProtoTimestampToLocalDate(Timestamp ts) {
        return Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public <T> CreateProfileResponse createProfile(CreateProfileDto request) {
        ProfileEntity entity = switch (request.getProfileType()) {
            case PATIENT -> PatientProfileEntity.builder()
                    .userId(UUID.fromString(request.getUserId()))
                    .fullName(request.getPatientProfile().getFullName())
                    .height(request.getPatientProfile().getHeight())
                    .weight(request.getPatientProfile().getWeight())
                    .afterTrauma(request.getPatientProfile().getAfterTrauma())
                    .afterOperation(request.getPatientProfile().getAfterOperation())
                    .limitedMovement(request.getPatientProfile().getLimitedMovement())
                    .birthday(convertProtoTimestampToLocalDate(request.getPatientProfile().getBirthday()))
                    .build();

            case DOCTOR -> DoctorProfileEntity.builder()
                    .userId(UUID.fromString(request.getUserId()))
                    .fullName(request.getDoctorProfile().getFullName())
                    .birthday(convertProtoTimestampToLocalDate(request.getDoctorProfile().getBirthday()))
                    .country(request.getDoctorProfile().getCountry())
                    .city(request.getDoctorProfile().getCity())
                    .specialization(request.getDoctorProfile().getDirection())
                    .hospitalName(request.getDoctorProfile().getHospitalName())
                    .build();

            case HOSPITAL -> HospitalProfileEntity.builder()
                    .userId(UUID.fromString(request.getUserId()))
                    .hospitalName(request.getHospitalProfile().getHospitalName())
                    .country(request.getHospitalProfile().getCountry())
                    .city(request.getHospitalProfile().getCity())
                    .address(request.getHospitalProfile().getAddress())
                    .type(request.getHospitalProfile().getHospitalType())
                    .build();
            default -> throw new IllegalArgumentException("Unknown profile type: " + request.getProfileType());
        };

        JpaRepository repository = profileService.getRepo(request.getProfileType());

        ProfileEntity saved = (ProfileEntity) repository.save(entity);

        UUID id = saved.getId();

        return CreateProfileResponse.newBuilder()
                .setProfileId(id.toString())
                .build();
    }
}

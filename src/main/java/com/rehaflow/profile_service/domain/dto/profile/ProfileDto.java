package com.rehaflow.profile_service.domain.dto.profile;

public sealed interface ProfileDto permits DoctorProfileDto, PatientProfileDto, HospitalProfileDto {
    com.rehaflow.profile_service.grpc.AccountType getProfileType();
}

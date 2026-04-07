package com.rehaflow.profile_service.domain.profile;

import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileEntity;
import com.rehaflow.profile_service.domain.dto.profile.*;
import com.rehaflow.profile_service.domain.hospital_profile.HospitalProfileEntity;
import com.rehaflow.profile_service.domain.patient_profile.PatientProfileEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileMapper {

    public ProfileDto toProfileDto(ProfileEntity profileEntity) {
        if (profileEntity instanceof DoctorProfileEntity doctor) {
            return mapDoctor(doctor);
        }

        if (profileEntity instanceof PatientProfileEntity patient) {
            return mapPatient(patient);
        }

        if (profileEntity instanceof HospitalProfileEntity hospital) {
            return mapHospital(hospital);
        }

        throw new IllegalArgumentException("Unknown profile type: " + profileEntity.getClass());
    }

    // ---------------- DOCTOR ----------------
    private DoctorProfileDto mapDoctor(DoctorProfileEntity doctor) {
        return DoctorProfileDto.builder()
                .fullName(doctor.getFullName())
                .direction(doctor.getSpecialization())
                .country(doctor.getCountry())
                .city(doctor.getCity())
                .birthday(doctor.getBirthday())
                .hospitalName(doctor.getHospitalName())
                .hospitalId(
                        doctor.getHospitalProfile() != null
                                ? doctor.getHospitalProfile().getId().toString()
                                : null
                )
                .build();
    }

    // ---------------- PATIENT ----------------
    private PatientProfileDto mapPatient(PatientProfileEntity patient) {
        return PatientProfileDto.builder()
                .fullName(patient.getFullName())
                .height(patient.getHeight())
                .weight(patient.getWeight())
                .afterOperation(patient.getAfterOperation())
                .afterTrauma(patient.getAfterTrauma())
                .limitedMovement(patient.getLimitedMovement())
                .birthday(patient.getBirthday())
                .hospitalId(
                        patient.getHospital() != null
                                ? patient.getHospital().getId().toString()
                                : null
                )
                .build();
    }

    // ---------------- HOSPITAL ----------------
    private HospitalProfileDto mapHospital(HospitalProfileEntity hospital) {
        return HospitalProfileDto.builder()
                .hospitalName(hospital.getHospitalName())
                .country(hospital.getCountry())
                .city(hospital.getCity())
                .address(hospital.getAddress())
                .hospitalType(hospital.getType())
                .build();
    }
}
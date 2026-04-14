package com.rehaflow.profile_service.app.public_profile.doctor.dto;


public record DoctorFilters (
        String birthday,
        String country,
        String city,
        String specialization
) {
}

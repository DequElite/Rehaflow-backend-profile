package com.rehaflow.profile_service.app.public_profile.hospital.dto;

public record HospitalFilters (
        String country,
        String city,
        String type,
        String address
) {
}

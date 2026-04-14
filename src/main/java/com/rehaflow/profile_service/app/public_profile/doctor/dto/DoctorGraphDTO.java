package com.rehaflow.profile_service.app.public_profile.doctor.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DoctorGraphDTO {
    private String id;
    private String full_name;
    private String birthday;
    private String country;
    private String city;
    private String specialization;
    private String hospital_name;
    private String hospital_profile;
    private List<String> protocols;
}
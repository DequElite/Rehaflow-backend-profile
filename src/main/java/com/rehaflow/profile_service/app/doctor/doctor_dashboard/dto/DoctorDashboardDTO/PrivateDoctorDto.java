package com.rehaflow.profile_service.app.doctor.doctor_dashboard.dto.DoctorDashboardDTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PrivateDoctorDto {
    private String id;
    private String full_name;
    private String birthday;
    private String country;
    private String city;
    private String specialization;
    private String hospital_name;
    private String hospital_profile;
    private List<String> protocols;
    private List<RehabRequestDto> rehabRequests;
}

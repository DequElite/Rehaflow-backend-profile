package com.rehaflow.profile_service.app.doctor.doctor_dashboard.dto.DoctorDashboardDTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DoctorPatientDto {
    private UUID id;
    private String full_name;
    private String birthday;
    private String country;
    private String city;
}

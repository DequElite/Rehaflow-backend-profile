package com.rehaflow.profile_service.app.doctor.doctor_dashboard.dto.DoctorDashboardDTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DoctorDashboardDto {
    private PrivateDoctorDto doctor;
    private List<DoctorPatientDto> patients_searched;
}

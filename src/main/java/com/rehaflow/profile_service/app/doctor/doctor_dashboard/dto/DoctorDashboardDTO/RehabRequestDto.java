package com.rehaflow.profile_service.app.doctor.doctor_dashboard.dto.DoctorDashboardDTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RehabRequestDto {
    private UUID id;
    private UUID patientId;
    private String patientName;
    private Integer patientAge;
    private String patientHeight;
    private String patientWeight;
    private Boolean patientOperation;
    private Boolean patientTrauma;
    private Boolean patientLimitedMovement;
    private String createdAt;
    private String diagnosisUrl;
    private String messageFromPatient;
    private String messageFromDoctor;
    private String status;
}

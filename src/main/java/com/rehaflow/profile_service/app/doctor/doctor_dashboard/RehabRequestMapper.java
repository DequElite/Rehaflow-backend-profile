package com.rehaflow.profile_service.app.doctor.doctor_dashboard;

import com.rehaflow.profile_service.app.doctor.doctor_dashboard.dto.DoctorDashboardDTO.RehabRequestDto;
import com.rehaflow.profile_service.domain.rehab_request.RehabRequestEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RehabRequestMapper {

    public RehabRequestDto toDto(RehabRequestEntity entity) {
        return RehabRequestDto.builder()
                .id(entity.getId())
                .patientId(entity.getPatient().getId())
                .patientName(entity.getPatient().getFullName())
                .patientAge(calculateAge(entity.getPatient().getBirthday()))
                .patientHeight(String.valueOf(entity.getPatient().getHeight()))
                .patientWeight(String.valueOf(entity.getPatient().getWeight()))
                .patientOperation(entity.getPatient().getAfterOperation())
                .patientTrauma(entity.getPatient().getAfterTrauma())
                .patientLimitedMovement(entity.getPatient().getLimitedMovement())
                .createdAt(entity.getCreatedAt().toString())
                .diagnosisUrl(entity.getDiagnosisFileUrl())
                .messageFromPatient(entity.getMessageFromPatient())
                .messageFromDoctor(entity.getMessageFromDoctor())
                .status(entity.getStatus().name())
                .build();
    }

    private Integer calculateAge(LocalDate birthday) {
        return birthday != null
                ? java.time.Period.between(birthday, java.time.LocalDate.now()).getYears()
                : null;
    }
}
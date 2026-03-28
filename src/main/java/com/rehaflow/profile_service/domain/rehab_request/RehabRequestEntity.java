package com.rehaflow.profile_service.domain.rehab_request;

import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileEntity;
import com.rehaflow.profile_service.domain.patient_profile.PatientProfileEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rehab_request")
public class RehabRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorProfileEntity doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientProfileEntity patient;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "diagnosis_file_url", nullable = false)
    private String diagnosisFileUrl;

    @Column(name = "message_from_patient")
    private String messageFromPatient;

    @Column(name = "message_from_doctor")
    private String messageFromDoctor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status = RequestStatus.PENDING;
}
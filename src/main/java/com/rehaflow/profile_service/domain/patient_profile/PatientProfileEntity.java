package com.rehaflow.profile_service.domain.patient_profile;

import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileEntity;
import com.rehaflow.profile_service.domain.hospital_profile.HospitalProfileEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "patient_profile")
public class PatientProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false, updatable = true, unique = true)
    private UUID userId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private Double weight;

    @Column(name = "after_operation", nullable = false)
    private Boolean afterOperation;

    @Column(name = "after_trauma", nullable = false)
    private Boolean afterTrauma;

    @Column(name = "limited_movement", nullable = false)
    private Boolean limitedMovement;

    @Column(nullable = false, updatable = false)
    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private HospitalProfileEntity hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorProfileEntity doctor;

    @Column(name = "active_protocol_id", nullable = true, updatable = true)
    private UUID activeProtocolId;
}

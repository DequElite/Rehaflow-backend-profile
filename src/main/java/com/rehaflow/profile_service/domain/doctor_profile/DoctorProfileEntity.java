package com.rehaflow.profile_service.domain.doctor_profile;

import com.rehaflow.profile_service.domain.hospital_profile.HospitalProfileEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "doctor_profile")
public class DoctorProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID id;

    private UUID userId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private HospitalProfileEntity hospitalProfile;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String specialization;

    @Column(name = "hospital_name")
    private String hospitalName;
}

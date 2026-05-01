package com.rehaflow.profile_service.domain.patient_profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientProfileRepository extends JpaRepository<PatientProfileEntity, UUID> {
}

package com.rehaflow.profile_service.domain.doctor_profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DoctorProfileRepository extends JpaRepository<DoctorProfileEntity, UUID> {
}

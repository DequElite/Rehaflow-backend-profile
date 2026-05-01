package com.rehaflow.profile_service.domain.hospital_profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitalProfileRepository extends JpaRepository<HospitalProfileEntity, UUID> {
}

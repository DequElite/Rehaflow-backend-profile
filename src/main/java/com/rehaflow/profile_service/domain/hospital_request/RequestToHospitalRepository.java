package com.rehaflow.profile_service.domain.hospital_request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequestToHospitalRepository extends JpaRepository<RequestToHospitalEntity, UUID> {
}

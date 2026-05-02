package com.rehaflow.profile_service.domain.rehab_request;

import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RehabRequestRepository extends JpaRepository<RehabRequestEntity, UUID> {
    List<RehabRequestEntity> findAllByDoctor_Id(UUID doctorId);
}

package com.rehaflow.profile_service.domain.rehab_request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RehabRequestRepository extends JpaRepository<RehabRequestEntity, UUID> {
}

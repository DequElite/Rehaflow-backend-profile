package com.rehaflow.profile_service.app.doctor.read_rehab_request;

import com.rehaflow.profile_service.domain.rehab_request.RehabRequestEntity;
import com.rehaflow.profile_service.domain.rehab_request.RehabRequestRepository;
import com.rehaflow.profile_service.domain.rehab_request.RequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RehabRequestStatusService {
    private final RehabRequestRepository rehabRequestRepository;

    public RehabRequestEntity updateRehabRequest(
            String messageToPatient,
            RequestStatus status,
            String id,
            String profileId
    ) {
        Optional<RehabRequestEntity> rehabRequest = this.rehabRequestRepository.findById(UUID.fromString(id));
        if(rehabRequest.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT_FOUND");
        }

        if(!rehabRequest.get().getDoctor().getId().equals(UUID.fromString(profileId))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
        }

        if(rehabRequest.get().getStatus().equals(RequestStatus.ACCEPTED) || rehabRequest.get().getStatus().equals(RequestStatus.DECLINED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD_REQUEST");
        }

        RehabRequestEntity updated = rehabRequest.get();
        updated.setStatus(status);
        updated.setMessageFromDoctor(messageToPatient);

        return this.rehabRequestRepository.save(updated);
    }
}

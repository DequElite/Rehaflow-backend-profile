package com.rehaflow.profile_service.app.doctor.read_rehab_request;

import com.rehaflow.profile_service.app.doctor.read_rehab_request.dto.ReadRehabRequestDTO;
import com.rehaflow.profile_service.domain.rehab_request.RehabRequestEntity;
import com.rehaflow.sharedlib.filters.auth.TokenHeaderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadRehabRequestService {
    private final RehabRequestStatusService rehabRequestStatusService;
    private final IndexRehabRequestService indexRehabRequestService;

    public void readRequest(TokenHeaderDTO user, ReadRehabRequestDTO body, String id) {
        RehabRequestEntity rehabRequest =
                this.rehabRequestStatusService.updateRehabRequest(
                        body.getMessageToPatient(),
                        body.getRequestStatus(),
                        id,
                        user.getProfileId()
                );

        this.indexRehabRequestService.indexRehabRequest(rehabRequest);
    }
}

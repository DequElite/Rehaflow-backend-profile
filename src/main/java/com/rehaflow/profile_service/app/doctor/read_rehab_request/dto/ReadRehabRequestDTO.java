package com.rehaflow.profile_service.app.doctor.read_rehab_request.dto;

import com.rehaflow.profile_service.domain.rehab_request.RequestStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReadRehabRequestDTO {
    @NotBlank(message = "messageToPatient is required")
    private String messageToPatient;

    @NotBlank(message = "requestStatus is required")
    private RequestStatus requestStatus;
}

package com.rehaflow.profile_service.app.doctor.read_rehab_request;

import com.rehaflow.profile_service.app.doctor.read_rehab_request.dto.ReadRehabRequestDTO;
import com.rehaflow.sharedlib.filters.auth.TokenHeaderDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor/rehab-requests")
@RequiredArgsConstructor
public class ReadRehabRequestController {
    private final ReadRehabRequestService service;

    @PostMapping("/read/{id}")
    public ResponseEntity<String> readRehabRequest(
            @AuthenticationPrincipal TokenHeaderDTO user,
            @Valid @RequestBody ReadRehabRequestDTO body,
            @PathVariable String id
            ) {
        this.service.readRequest(
                user,
                body,
                id
        );
        return ResponseEntity.ok("Rehab request marked as read");
    }

}

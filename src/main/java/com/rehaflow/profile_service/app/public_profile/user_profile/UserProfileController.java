package com.rehaflow.profile_service.app.public_profile.user_profile;

import com.rehaflow.profile_service.domain.dto.profile.ProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
@Slf4j
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService service;

    @GetMapping
    public ResponseEntity<ProfileDto> getUserProfile(
            @RequestHeader(value = "auth", required = true) String authToken
    ) {
        return ResponseEntity.ok(this.service.getUser(authToken));
    }

}

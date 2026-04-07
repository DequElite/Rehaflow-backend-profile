package com.rehaflow.profile_service.app.public_profile.user_profile;

import com.rehaflow.profile_service.app.public_profile.user_profile.dto.TokenHeaderDto;
import com.rehaflow.profile_service.domain.dto.profile.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final TokenHeaderValidationService tokenHeaderValidationService;
    private final GetUserProfileService getUserProfileService;

    public ProfileDto getUser(String authToken) {
        TokenHeaderDto tokenHeaderDto = this.tokenHeaderValidationService.validateToken(authToken);

        return this.getUserProfileService.getUserProfile(tokenHeaderDto);
    }
}

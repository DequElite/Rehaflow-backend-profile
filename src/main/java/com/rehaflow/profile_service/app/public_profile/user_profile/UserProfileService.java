package com.rehaflow.profile_service.app.public_profile.user_profile;

import com.rehaflow.profile_service.domain.dto.profile.ProfileDto;
import com.rehaflow.sharedlib.filters.auth.TokenHeaderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final GetUserProfileService getUserProfileService;

    public ProfileDto getUser(TokenHeaderDTO user) {
        return this.getUserProfileService.getUserProfile(user);
    }
}

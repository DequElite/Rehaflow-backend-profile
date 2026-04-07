package com.rehaflow.profile_service.app.public_profile.user_profile;

import com.rehaflow.profile_service.app.public_profile.user_profile.dto.TokenHeaderDto;
import com.rehaflow.profile_service.domain.dto.profile.ProfileDto;
import com.rehaflow.profile_service.domain.profile.ProfileEntity;
import com.rehaflow.profile_service.domain.profile.ProfileMapper;
import com.rehaflow.profile_service.domain.profile.ProfileService;
import com.rehaflow.profile_service.domain.session.SessionEntity;
import com.rehaflow.profile_service.infrastructure.redis.SessionCacheService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetUserProfileService {
    private final SessionCacheService sessionCacheService;
    private final ProfileService profileService;
    private final ProfileMapper profileMapper;

    public ProfileDto getUserProfile(TokenHeaderDto tokenHeaderDto) {
        Optional<SessionEntity> sessionEntity = this.sessionCacheService.getSession(tokenHeaderDto.getJti());
        if(sessionEntity.isPresent()){
            return sessionEntity.get().getProfile();
        }

        JpaRepository<? extends ProfileEntity, UUID> repository =
                profileService.getRepo(com.rehaflow.profile_service.grpc.AccountType.valueOf(tokenHeaderDto.getProfileType()));

        Object profile = repository
                .findById(UUID.fromString(tokenHeaderDto.getProfileId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Profile not found"));

        if(!(profile instanceof ProfileEntity profileEntity)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unknown profile type: " + tokenHeaderDto.getProfileType());
        }

        if(!profileEntity.getUserId().equals(UUID.fromString(tokenHeaderDto.getSub()))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unknown profile type: " + tokenHeaderDto.getProfileType());
        }

        ProfileDto profileDto = profileMapper.toProfileDto(profileEntity);

        sessionCacheService.saveSession(
                UUID.fromString(tokenHeaderDto.getJti()),
                UUID.fromString(tokenHeaderDto.getSub()),
                UUID.fromString(tokenHeaderDto.getProfileId()),
                profileDto
        );

        return profileDto;
    }

}

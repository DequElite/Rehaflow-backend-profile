package com.rehaflow.profile_service.infrastructure.redis;

import com.rehaflow.profile_service.domain.dto.profile.ProfileDto;
import com.rehaflow.profile_service.domain.session.SessionEntity;
import com.rehaflow.profile_service.domain.session.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionCacheService {
    @Value("${jwt.ttl.session}")
    private long sessionTtl;

    private final SessionRepository sessionRepository;

    public void saveSession(UUID accessTokenId, UUID userId, UUID profileId, ProfileDto profile) {
        SessionEntity session = SessionEntity.builder()
                .id(accessTokenId.toString())
                .userId(userId.toString())
                .profileId(profileId.toString())
                .profileType(profile.getProfileType())
                .profile(profile)
                .TTL(sessionTtl)
                .build();

        sessionRepository.save(session);
    }

    public Optional<SessionEntity> getSession(String accessTokenId) {
        return sessionRepository.findById(accessTokenId);
    }
}

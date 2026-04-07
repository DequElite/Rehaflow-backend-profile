package com.rehaflow.profile_service.app.public_profile.user_profile.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenHeaderDto {
    private String jti;
    private String sub;
    private String profileId;
    private String profileType;
}

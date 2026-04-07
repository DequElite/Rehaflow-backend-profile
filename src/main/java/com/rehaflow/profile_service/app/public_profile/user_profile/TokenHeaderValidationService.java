package com.rehaflow.profile_service.app.public_profile.user_profile;

import com.rehaflow.profile_service.app.public_profile.user_profile.dto.TokenHeaderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.rehaflow.sharedlib.services.jwt.JwtService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenHeaderValidationService {
    private final JwtService jwtService;

    @Value("${jwt.secret.session}")
    private String accessTokenSecret;

    public TokenHeaderDto validateToken(String authHeader) {
        String[] authHeaderPats = authHeader.split(" ");
        if(!authHeaderPats[0].equals("Bearer")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
        }

        String token = authHeaderPats[1];
        if(token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
        }

        Map<String, Object> decodedToken;
        try {
            decodedToken = this.jwtService.verifyToken(token, accessTokenSecret);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "REFRESH_TOKEN_UNVALID");
        }

        return TokenHeaderDto.builder()
                .jti(decodedToken.get("jti").toString())
                .sub(decodedToken.get("sub").toString())
                .profileId(decodedToken.get("profileId").toString())
                .profileType(decodedToken.get("profileType").toString())
                .build();
    }
}

package com.rehaflow.profile_service.domain.session;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rehaflow.profile_service.domain.dto.profile.DoctorProfileDto;
import com.rehaflow.profile_service.domain.dto.profile.HospitalProfileDto;
import com.rehaflow.profile_service.domain.dto.profile.PatientProfileDto;
import com.rehaflow.profile_service.domain.dto.profile.ProfileDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "profileType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DoctorProfileDto.class),
        @JsonSubTypes.Type(value = PatientProfileDto.class),
        @JsonSubTypes.Type(value = HospitalProfileDto.class)
})
@RedisHash("sessions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionEntity {
    @Id
    private String id;

    private String userId;
    private String profileId;
    private com.rehaflow.profile_service.grpc.AccountType profileType;
    private ProfileDto profile;

    @TimeToLive
    private long TTL;
}

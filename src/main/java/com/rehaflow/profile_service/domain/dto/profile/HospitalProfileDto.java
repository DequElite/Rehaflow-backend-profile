package com.rehaflow.profile_service.domain.dto.profile;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonTypeName("HOSPITAL")
@Getter
@Setter
@AllArgsConstructor
@Builder
public final class HospitalProfileDto implements ProfileDto {

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @NotBlank
    private String hospitalType;

    @NotBlank
    private String hospitalName;

    @Override
    public com.rehaflow.profile_service.grpc.AccountType getProfileType() {
        return com.rehaflow.profile_service.grpc.AccountType.PATIENT;
    }
}

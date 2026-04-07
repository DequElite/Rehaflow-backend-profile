package com.rehaflow.profile_service.domain.dto.profile;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@JsonTypeName("DOCTOR")
@Getter
@Setter
@AllArgsConstructor
@Builder
public final class DoctorProfileDto implements ProfileDto {

    @NotBlank
    private String fullName;

    @NotBlank
    private String direction;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotNull
    private LocalDate birthday;

    @NotBlank
    private String hospitalName;

    private String hospitalId;

    @Override
    public com.rehaflow.profile_service.grpc.AccountType getProfileType() {
        return com.rehaflow.profile_service.grpc.AccountType.DOCTOR;
    }

}

package com.rehaflow.profile_service.domain.dto.profile;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@JsonTypeName("PATIENT")
@Getter
@Setter
@AllArgsConstructor
@Builder
public final class PatientProfileDto implements ProfileDto {

    @NotBlank
    private String fullName;

    @Min(30)
    @Max(300)
    private Integer height;

    @DecimalMin("1.0")
    @DecimalMax("500.0")
    private Double weight;

    private Boolean afterOperation;
    private Boolean afterTrauma;
    private Boolean limitedMovement;

    @NotNull
    private LocalDate birthday;

    private String hospitalId;

    @Override
    public com.rehaflow.profile_service.grpc.AccountType getProfileType() {
        return com.rehaflow.profile_service.grpc.AccountType.PATIENT;
    }
}

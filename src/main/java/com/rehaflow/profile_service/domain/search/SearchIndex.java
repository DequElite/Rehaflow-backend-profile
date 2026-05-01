package com.rehaflow.profile_service.domain.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SearchIndex {
    DOCTORS("doctors"),
    HOSPITALS("hospitals"),
    PROTOCOLS("protocols"),
    DOCTOR_PATIENTS("doctor_patients");

    private final String index;
}

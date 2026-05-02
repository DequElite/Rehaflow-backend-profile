package com.rehaflow.profile_service.app.doctor.doctor_dashboard;

import com.rehaflow.profile_service.app.doctor.doctor_dashboard.dto.DoctorDashboardDTO.DoctorDashboardDto;
import com.rehaflow.profile_service.app.doctor.doctor_dashboard.dto.Filters.PatientFiltersDto;
import com.rehaflow.sharedlib.filters.auth.TokenHeaderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class DoctorDashboardController {

    private final DoctorDashboardService doctorDashboardService;

    @QueryMapping
    public DoctorDashboardDto doctorDashboard(
            @Argument String patientSearch,
            @Argument PatientFiltersDto patientFilter,
            @AuthenticationPrincipal TokenHeaderDTO user
            ){
        return this.doctorDashboardService.getDashboard(patientSearch, patientFilter, user);
    }
}

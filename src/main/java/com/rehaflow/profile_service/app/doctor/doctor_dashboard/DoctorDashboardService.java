package com.rehaflow.profile_service.app.doctor.doctor_dashboard;

import com.rehaflow.profile_service.app.doctor.doctor_dashboard.dto.DoctorDashboardDTO.DoctorDashboardDto;
import com.rehaflow.profile_service.app.doctor.doctor_dashboard.dto.Filters.PatientFiltersDto;
import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileEntity;
import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileRepository;
import com.rehaflow.profile_service.domain.rehab_request.RehabRequestEntity;
import com.rehaflow.profile_service.domain.rehab_request.RehabRequestRepository;
import com.rehaflow.profile_service.domain.search.SearchIndex;
import com.rehaflow.profile_service.infrastructure.grpc.search.SearchGrpcClient;
import com.rehaflow.sharedlib.filters.auth.TokenHeaderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DoctorDashboardService {
    private final DoctorProfileRepository doctorProfileRepository;
    private final RehabRequestRepository rehabRequestRepository;
    private final DoctorDtoMapper mapper;

    private final SearchGrpcClient searchGrpcClient;

    public DoctorDashboardDto getDashboard(String patientSearch, PatientFiltersDto patientFilter, TokenHeaderDTO user) {
        Optional<DoctorProfileEntity> doctorProfileEntity = this.doctorProfileRepository.findById(UUID.fromString(user.getProfileId()));
        if(doctorProfileEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<RehabRequestEntity> rehabRequestEntities = this.rehabRequestRepository.findAllByDoctor_Id(doctorProfileEntity.get().getId());

        boolean hasSearch = patientSearch != null && !patientSearch.isBlank();
        boolean hasFilter = patientFilter != null;

        if (!hasSearch && !hasFilter) {
            return DoctorDashboardDto.builder()
                    .doctor(mapper.toPrivateDto(doctorProfileEntity.get(), rehabRequestEntities))
                    .patients_searched(List.of())
                    .build();
        }
    }
}

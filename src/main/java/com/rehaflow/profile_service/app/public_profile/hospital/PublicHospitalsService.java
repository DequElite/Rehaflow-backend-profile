package com.rehaflow.profile_service.app.public_profile.hospital;

import com.rehaflow.profile_service.app.public_profile.doctor.DoctorGraphMapper;
import com.rehaflow.profile_service.app.public_profile.doctor.dto.DoctorGraphDTO;
import com.rehaflow.profile_service.app.public_profile.hospital.dto.HospitalFilters;
import com.rehaflow.profile_service.app.public_profile.hospital.dto.HospitalGraphDTO;
import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileRepository;
import com.rehaflow.profile_service.domain.hospital_profile.HospitalProfileEntity;
import com.rehaflow.profile_service.domain.hospital_profile.HospitalProfileRepository;
import com.rehaflow.profile_service.domain.search.SearchIndex;
import com.rehaflow.profile_service.infrastructure.grpc.search.SearchGrpcClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import com.rehaflow.profile_service.grpc.SearchResponse;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PublicHospitalsService {
    private final SearchGrpcClient searchGrpcClient;
    private final HospitalProfileRepository hospitalProfileRepository;
    private final DoctorProfileRepository doctorRepository;
    private final HospitalGraphMapper hospitalGraphMapper;
    private final DoctorGraphMapper doctorMapper;

    public List<HospitalGraphDTO> search(
            String search,
            HospitalFilters filters
    ) {
        Map<String, String> filtersMap = new HashMap<>();

        if (filters.address() != null) {
            filtersMap.put("address", filters.address());
        }
        if (filters.country() != null) {
            filtersMap.put("country", filters.country());
        }
        if (filters.city() != null) {
            filtersMap.put("city", filters.city());
        }
        if (filters.type() != null) {
            filtersMap.put("type", filters.type());
        }

        SearchResponse searchResponse = this.searchGrpcClient.search(
                SearchIndex.HOSPITALS,
                search,
                filtersMap
        );

        List<String> ids = searchResponse.getIdsList();

        List<UUID> uuids = ids.stream()
                .map(id -> {
                    try {
                        return UUID.fromString(id);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        List<HospitalProfileEntity> hospitalProfileEntityList = this.hospitalProfileRepository.findAllById(uuids);

        List<HospitalGraphDTO> result = hospitalProfileEntityList.stream()
                .map(hospital -> {
                    List<DoctorGraphDTO> doctors = doctorRepository
                            .findAllByHospitalProfile_Id(hospital.getId())
                            .stream()
                            .map(doctorMapper::toDto)
                            .toList();

                    return hospitalGraphMapper.toDto(hospital, doctors);
                })
                .toList();

        return result;
    }

}

package com.rehaflow.profile_service.app.public_profile.doctor;

import com.rehaflow.profile_service.app.public_profile.doctor.dto.DoctorFilters;
import com.rehaflow.profile_service.app.public_profile.doctor.dto.DoctorGraphDTO;
import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileEntity;
import com.rehaflow.profile_service.domain.doctor_profile.DoctorProfileRepository;
import com.rehaflow.profile_service.domain.search.SearchIndex;
import com.rehaflow.profile_service.infrastructure.grpc.search.SearchGrpcClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.rehaflow.profile_service.grpc.SearchResponse;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicDoctorsService {
    private final SearchGrpcClient searchGrpcClient;
    private final DoctorProfileRepository doctorProfileRepository;
    private final DoctorGraphMapper mapper;

    public List<DoctorGraphDTO> search(
        String search,
        DoctorFilters filters
    ) {
        log.info("SEEEARCH");

        Map<String, String> filtersMap = new HashMap<>();

        if (filters != null) {
            if (filters.birthday() != null) {
                filtersMap.put("birthday", filters.birthday());
            }
            if (filters.country() != null) {
                filtersMap.put("country", filters.country());
            }
            if (filters.city() != null) {
                filtersMap.put("city", filters.city());
            }
            if (filters.specialization() != null) {
                filtersMap.put("specialization", filters.specialization());
            }
        }

        SearchResponse searchResponse = this.searchGrpcClient.search(
                SearchIndex.DOCTORS,
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

        List<DoctorProfileEntity> doctorProfileEntityList = this.doctorProfileRepository.findAllByUserIdIn(uuids);

        List<DoctorGraphDTO> doctors = doctorProfileEntityList.stream()
                .map(mapper::toDto)
                .toList();

        log.info("SEEEARCHSF ff: {}", doctors);

        return doctors;
    }
}

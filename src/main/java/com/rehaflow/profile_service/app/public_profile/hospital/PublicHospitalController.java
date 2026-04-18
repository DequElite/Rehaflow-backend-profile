package com.rehaflow.profile_service.app.public_profile.hospital;

import com.rehaflow.profile_service.app.public_profile.hospital.dto.HospitalFilters;
import com.rehaflow.profile_service.app.public_profile.hospital.dto.HospitalGraphDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PublicHospitalController {

    private final GetHospitalByIdService getHospitalByIdService;
    private final PublicHospitalsService publicHospitalsService;

    @QueryMapping
    public HospitalGraphDTO hospitalById(@Argument String id) {
        return this.getHospitalByIdService.getHospitalById(id);
    }

    @QueryMapping
    public List<HospitalGraphDTO> publicHospitals(
            @Argument String search,
            @Argument HospitalFilters filters
            ) {
        return publicHospitalsService.search(search, filters);
    }

}

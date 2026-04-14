package com.rehaflow.profile_service.app.public_profile.doctor;

import com.rehaflow.profile_service.app.public_profile.doctor.dto.DoctorGraphDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PublicDoctorController {

    private final GetDoctorByIdService getDoctorByIdService;

    @QueryMapping
    public DoctorGraphDTO doctorById(@Argument String id) {
        return this.getDoctorByIdService.getDoctorById(id);
    }
}

package com.rehaflow.profile_service.app.public_profile.hospital.dto;

import com.rehaflow.profile_service.app.public_profile.doctor.dto.DoctorGraphDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HospitalGraphDTO {
    private String id;
    private String name;
    private String country;
    private String city;
    private String address;
    private String type;
    private List<DoctorGraphDTO> doctors;
}

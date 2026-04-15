package com.rehaflow.profile_service.infrastructure.grpc.search;

import com.rehaflow.auth_service.domain.dto.profile.DoctorProfileDto;
import com.rehaflow.auth_service.domain.dto.profile.HospitalProfileDto;
import com.rehaflow.auth_service.domain.dto.profile.ProfileDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SearchProtoMapper {

    public Map<String, String> toMap(ProfileDto profile) throws Exception {
        Map<String, String> map = new HashMap<>();

        if (profile instanceof DoctorProfileDto doctor) {
            map.put("full_name", doctor.getFullName());
            map.put("specialization", doctor.getDirection());
            map.put("country", doctor.getCountry());
            map.put("city", doctor.getCity());
            map.put("hospital_id", doctor.getHospitalId());
            if (doctor.getHospitalId() != null) map.put("hospitalId", doctor.getHospitalId());
        } else if (profile instanceof HospitalProfileDto hospital) {
            map.put("hospital_name", hospital.getHospitalName());
            map.put("country", hospital.getCountry());
            map.put("city", hospital.getCity());
            map.put("type", hospital.getHospitalType());
        } else {
            throw new Exception("Illegal profile format");
        }

        return map;
    }
}
package com.rehaflow.profile_service.app.doctor.read_rehab_request;

import com.rehaflow.profile_service.domain.patient_profile.PatientProfileEntity;
import com.rehaflow.profile_service.domain.rehab_request.RehabRequestEntity;
import com.rehaflow.profile_service.domain.search.SearchIndex;
import com.rehaflow.profile_service.infrastructure.grpc.index_search.IndexSearchGrpcClient;
import com.rehaflow.profile_service.infrastructure.grpc.index_search.IndexSearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class IndexRehabRequestService {
    private final IndexSearchGrpcClient indexSearchGrpcClient;
    private final IndexSearchMapper indexSearchMapper;

    public void indexRehabRequest(RehabRequestEntity rehabRequest){
        PatientProfileEntity patientProfile = rehabRequest.getPatient();

        com.rehaflow.profile_service.grpc.IndexDocumentResponse grpcResponse =
                this.indexSearchGrpcClient.indexDocument(
                        SearchIndex.DOCTOR_PATIENTS,
                        patientProfile.getId().toString(),
                        indexSearchMapper.toMapDoctorPatient(patientProfile)
                );
        if(grpcResponse.getError()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
        }
    }
}

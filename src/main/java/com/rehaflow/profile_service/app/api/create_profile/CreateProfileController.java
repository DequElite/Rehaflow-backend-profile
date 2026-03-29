package com.rehaflow.profile_service.app.api.create_profile;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import com.rehaflow.profile_service.grpc.CreateProfileServiceGrpc;
import com.rehaflow.profile_service.grpc.CreateProfileDto;
import com.rehaflow.profile_service.grpc.CreateProfileResponse;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class CreateProfileController extends CreateProfileServiceGrpc.CreateProfileServiceImplBase {
    private final CreateProfileService service;

    @Override
    public void createProfileAndReturn(CreateProfileDto request,
                                       StreamObserver<CreateProfileResponse> responseObserver) {
        try {
            CreateProfileResponse response = this.service.createProfile(request);

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {

            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );

        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription("Internal server error")
                            .asRuntimeException()
            );
        }
    }
}

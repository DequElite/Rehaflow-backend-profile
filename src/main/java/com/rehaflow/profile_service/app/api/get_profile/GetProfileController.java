package com.rehaflow.profile_service.app.api.get_profile;

import com.rehaflow.profile_service.grpc.GetProfileServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class GetProfileController extends GetProfileServiceGrpc.GetProfileServiceImplBase {
    @Override
    public void getProfileByID(
            com.rehaflow.profile_service.grpc.GetProfileByIdRequest request,
            StreamObserver<com.rehaflow.profile_service.grpc.GetProfileByIdResponse> responseObserver
    ) {
        try {

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

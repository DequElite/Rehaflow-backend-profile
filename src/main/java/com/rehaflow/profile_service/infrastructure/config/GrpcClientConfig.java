package com.rehaflow.profile_service.infrastructure.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {
    @Value("${grpc.search.host}")
    private String searchHost;

    @Value("${grpc.search.port}")
    private int searchPort;

    @Bean(name = "searchChannel")
    public ManagedChannel searchChannel() {
        return ManagedChannelBuilder
                .forAddress(searchHost, searchPort)
                .usePlaintext()
                .build();
    }

    @Bean
    public com.rehaflow.profile_service.grpc.SearchServiceGrpc.SearchServiceBlockingStub serviceBlockingStub(
            @Qualifier("searchChannel") ManagedChannel managedChannel
    ){
        return com.rehaflow.profile_service.grpc.SearchServiceGrpc.newBlockingStub(managedChannel);
    }

}

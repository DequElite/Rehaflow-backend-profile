package com.rehaflow.profile_service.infrastructure.grpc.search;

import com.rehaflow.profile_service.domain.search.SearchIndex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchGrpcClient {
    private final com.rehaflow.profile_service.grpc.SearchServiceGrpc.SearchServiceBlockingStub serviceBlockingStub;

    public com.rehaflow.profile_service.grpc.SearchResponse search(
            SearchIndex index,
            String query,
            Map<String, String> filters
    ) {
        try {

            com.rehaflow.profile_service.grpc.SearchRequest request =
                    com.rehaflow.profile_service.grpc.SearchRequest.newBuilder()
                    .setIndex(index.getIndex())
                    .setQuery(query)
                    .putAllFilters(filters)
                    .build();

            com.rehaflow.profile_service.grpc.SearchResponse response =
                    serviceBlockingStub
                            .withDeadlineAfter(10, TimeUnit.SECONDS)
                            .search(request);

            return response;
        } catch (Exception e) {
            log.error("Error at indexDocument: ", e);

            throw new RuntimeException(e);
        }
    }
}

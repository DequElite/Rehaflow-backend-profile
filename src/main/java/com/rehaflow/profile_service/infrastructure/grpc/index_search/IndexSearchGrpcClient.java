package com.rehaflow.profile_service.infrastructure.grpc.index_search;

import com.rehaflow.profile_service.domain.search.SearchIndex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.rehaflow.profile_service.grpc.IndexDocumentServiceGrpc;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class IndexSearchGrpcClient {
    private final IndexDocumentServiceGrpc.IndexDocumentServiceBlockingStub indexDocumentServiceBlockingStub;

    public com.rehaflow.profile_service.grpc.IndexDocumentResponse indexDocument(
            SearchIndex index,
            String baseId,
            Map<String, String> map
    ) {
        try {
            com.rehaflow.profile_service.grpc.IndexDocumentRequest request = com.rehaflow.profile_service.grpc.IndexDocumentRequest.newBuilder()
                    .setIndex(index.getIndex())
                    .setBaseId(baseId)
                    .putAllData(map)
                    .build();

            return indexDocumentServiceBlockingStub
                    .withDeadlineAfter(10, TimeUnit.SECONDS)
                    .indexDocument(request);
        } catch (Exception e) {
            log.error("Error at indexDocument: ", e);

            throw new RuntimeException(e);
        }
    }

}

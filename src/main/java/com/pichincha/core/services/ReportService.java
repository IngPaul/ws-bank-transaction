package com.pichincha.core.services;

import com.pichincha.core.model.PostAccountStatusRequest;
import com.pichincha.core.model.PostAccountStatusResponse;
import reactor.core.publisher.Flux;

public interface ReportService {
    Flux<PostAccountStatusResponse> getReport(PostAccountStatusRequest request);
}

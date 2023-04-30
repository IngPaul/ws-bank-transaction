package com.pichincha.core.controllers;

import com.pichincha.core.model.PostAccountStatusRequest;
import com.pichincha.core.model.PostAccountStatusResponse;
import com.pichincha.core.services.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ReportApiImpl implements ReportApi {
    private final ReportService reportService;

    @Override
    public Mono<ResponseEntity<Flux<PostAccountStatusResponse>>> postAccountStatusGenerate(Mono<PostAccountStatusRequest> postAccountStatusRequest, ServerWebExchange exchange) {
        return postAccountStatusRequest.map(request -> reportService.getReport(request))
                .map(ResponseEntity::ok);
    }
}

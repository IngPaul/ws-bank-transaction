package com.pichincha.core.controllers;

import Util.MockData;
import com.pichincha.core.model.*;
import com.pichincha.core.services.ReportService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ReportApiImplTest {
    @Mock private ReportService reportService;
    @InjectMocks
    private ReportApiImpl bankTransactionApi;

    @Before
    public void setup() {

    }
    @Test
    public void testBankTransactionAccountStatusGeneratePost() {
        PostAccountStatusRequest request = MockData.getPostAccountStatusRequest();
        PostAccountStatusResponse response = MockData.getPostAccountStatusResponse();
        Mockito.when(reportService.getReport(request)).thenReturn(Flux.just(response));
        Mono<PostAccountStatusRequest> requestMono = Mono.just(request);
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.postAccountStatusGenerate(requestMono, client))
                .expectNextCount(1)
                .verifyComplete();
    }
}


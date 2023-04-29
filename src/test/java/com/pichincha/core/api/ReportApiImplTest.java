package com.pichincha.core.api;

import Util.MockData;
import com.pichincha.core.controllers.BankTransactionApiImpl;
import com.pichincha.core.controllers.ReportApiImpl;
import com.pichincha.core.model.*;
import com.pichincha.core.services.AccountService;
import com.pichincha.core.services.CustomerService;
import com.pichincha.core.services.ReportService;
import com.pichincha.core.services.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ReportApiImplTest {
    @Mock private ReportService reportService;
    @InjectMocks
    private ReportApiImpl bankTransactionApi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
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


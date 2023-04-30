package com.pichincha.core.controllers;

import Util.MockData;
import com.pichincha.core.model.*;
import com.pichincha.core.services.TransactionService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BankTransactionApiImplTest {

    @Mock private TransactionService transactionService;

    @InjectMocks
    private BankTransactionApiImpl bankTransactionApi;



    @Before
    public void setup() {

    }

    @Test
    public void test_getBankTransaction() {
        Transaction transaction1 = MockData.getTransaction();
        Transaction transaction2 = MockData.getTransaction();
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        Mockito.when(transactionService.getBankTransaction()).thenReturn(Flux.fromIterable(transactions));

        ServerWebExchange exchange = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.getBankTransaction(exchange))
                .expectNextMatches(response -> {
                    List<Transaction> transactionList = response.getBody().collectList().block();
                    return response.getStatusCode().equals(HttpStatus.OK) && transactionList.size() == 2 &&
                            transactionList.contains(transaction1) && transactionList.contains(transaction2);
                })
                .verifyComplete();
    }
    @Test
    public void test_deleteBankTransaction() {
        Long idTransaction = 1L;
        Mockito.when(transactionService.deleteTransaction(Mockito.anyLong())).thenReturn(Mono.empty());

        ServerWebExchange exchange = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.deleteBankTransaction(idTransaction, exchange))
                .expectComplete()
                .verify();
    }
    @Test
    public void test_getBankTransactionById() {
        Transaction transaction = MockData.getTransaction();
        Long id = 1L;
        Mockito.when(transactionService.getBankTransactionById(id)).thenReturn(Mono.just(transaction));
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.getBankTransactionById(id, client))
                .expectNextCount(1)
                .verifyComplete();
    }



    @Test
    public void test_postTransaction() {
        TransactionRequest request = MockData.getTransactionRequest();
        Mockito.when(transactionService.postTransaction(request)).thenReturn(Mono.empty());
        Mono<TransactionRequest> requestMono = Mono.just(request);
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.postTransaction(requestMono, client))
                .expectNextCount(0)
                .verifyComplete();
    }

}


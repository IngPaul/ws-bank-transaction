package com.pichincha.core.api;

import Util.MockData;
import com.pichincha.core.controllers.AccountApiImpl;
import com.pichincha.core.controllers.BankTransactionApiImpl;
import com.pichincha.core.model.*;
import com.pichincha.core.services.AccountService;
import com.pichincha.core.services.CustomerService;
import com.pichincha.core.services.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest()
public class AccountApiImplTest {

    @Mock
    private AccountService accountService;
    @InjectMocks
    private AccountApiImpl bankTransactionApi;



    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getAccount() {
        Mockito.when(accountService.getAccount()).thenReturn(Flux.empty());
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.getAccount(client))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void test_getAccountById() {
        Mockito.when(accountService.deleteAccount(Mockito.anyLong())).thenReturn(Mono.empty());
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.getAccountById(1L, client))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void test_deleteAccount() {
        Account account = MockData.getAccount();
        Mockito.when(accountService.findAccountById(Mockito.anyLong())).thenReturn(Mono.just(account));
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.deleteAccount(1L, client))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void test_putAccount() {
        Account account = MockData.getAccount();
        account.setInitialBalance(BigDecimal.valueOf(30000));
        Mockito.when(accountService.putAccount(Mockito.anyLong(), Mockito.any(Account.class))).thenReturn(Mono.just(account));
        Mono<Account> accountMono = Mono.just(account);
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.putAccount(1L, accountMono, client))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void test_postAccount() {
        Account account = MockData.getAccount();
        Mockito.when(accountService.postAccount(Mockito.any(Account.class))).thenReturn(Mono.just(account));
        Mono<Account> accountMono = Mono.just(account);
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.postAccount(accountMono, client))
                .expectNextCount(1)
                .verifyComplete();
    }
}


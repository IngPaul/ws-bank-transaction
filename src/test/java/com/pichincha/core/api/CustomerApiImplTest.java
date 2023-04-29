package com.pichincha.core.api;

import Util.MockData;
import com.pichincha.core.controllers.BankTransactionApiImpl;
import com.pichincha.core.controllers.CustomerApiImpl;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CustomerApiImplTest {
    @Mock private CustomerService customerService;

    @InjectMocks
    private CustomerApiImpl bankTransactionApi;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getCustomer() {
        Mockito.when(customerService.getCustomer()).thenReturn(Flux.just(MockData.getCustomer()));
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.getCustomer(client))
                .expectNextMatches(response -> response.getStatusCode().equals(HttpStatus.OK))
                .verifyComplete();
    }
    @Test
    public void test_getCustomerById() {
        Customer customer = MockData.getCustomer();
        Mockito.when(customerService.getCustomerById(Mockito.anyLong())).thenReturn(Mono.just(customer));
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.getCustomerById(1L, client))
                .expectNextMatches(response -> response.getStatusCode().equals(HttpStatus.OK) && response.getBody().equals(customer))
                .verifyComplete();
    }

    @Test
    public void test_putCustomer() {
        Customer customer = MockData.getCustomer();
        Mockito.when(customerService.putCustomer(Mockito.anyLong(), Mockito.any(Customer.class))).thenReturn(Mono.just(customer));
        Mono<Customer> customerMono = Mono.just(customer);
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.putCustomer(1L, customerMono, client))
                .expectNextMatches(response -> response.getStatusCode().equals(HttpStatus.OK))
                .verifyComplete();
    }

    @Test
    public void test_postCustomer() {
        Customer customer = MockData.getCustomer();
        Mockito.when(customerService.postCustomer(Mockito.any(Customer.class))).thenReturn(Mono.just(customer));
        Mono<Customer> customerMono = Mono.just(customer);
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.postCustomer(customerMono, client))
                .expectNextMatches(response -> response.getStatusCode().equals(HttpStatus.CREATED) && response.getBody().equals(customer))
                .verifyComplete();
    }
}


package com.pichincha.core.api;

import Util.MockData;
import com.pichincha.core.controllers.BankTransactionApiImpl;
import com.pichincha.core.model.*;
import com.pichincha.core.services.AccountService;
import com.pichincha.core.services.CustomerService;
import com.pichincha.core.services.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BankTransactionApiImplTest {

    @Mock
    private AccountService accountService;
    @Mock private CustomerService customerService;
    @Mock private TransactionService transactionService;

    @InjectMocks
    private BankTransactionApiImpl bankTransactionApi;



    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBankTransactionAccountsGet() {
        Mockito.when(accountService.findAllAccounts()).thenReturn(Flux.empty());
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionAccountsGet(client))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void testBankTransactionAccountsIdAccountDelete() {
        Mockito.when(accountService.deleteAccount(Mockito.anyLong())).thenReturn(Mono.empty());
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionAccountsIdAccountDelete(1L, client))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void testBankTransactionAccountsIdAccountGet() {
        Account account = MockData.getAccount();
        Mockito.when(accountService.findAccountById(Mockito.anyLong())).thenReturn(Mono.just(account));
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionAccountsIdAccountGet(1L, client))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void testBankTransactionAccountsIdAccountPut() {
        Account account = MockData.getAccount();
        account.setInitialBalance(BigDecimal.valueOf(30000));
        Mockito.when(accountService.updateAccount(Mockito.anyLong(), Mockito.any(Account.class))).thenReturn(Mono.just(account));
        Mono<Account> accountMono = Mono.just(account);
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionAccountsIdAccountPut(1L, accountMono, client))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void testBankTransactionAccountsPost() {
        Account account = MockData.getAccount();
        Mockito.when(accountService.saveAccount(Mockito.any(Account.class))).thenReturn(Mono.just(account));
        Mono<Account> accountMono = Mono.just(account);
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionAccountsPost(accountMono, client))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void testBankTransactionCustomersGet() {
        Mockito.when(customerService.findAllCustomers()).thenReturn(Flux.just(MockData.getCustomer()));
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionCustomersGet(client))
                .expectNextMatches(response -> response.getStatusCode().equals(HttpStatus.OK))
                .verifyComplete();
    }
    @Test
    public void testBankTransactionCustomersIdCustomerGet() {
        Customer customer = MockData.getCustomer();
        Mockito.when(customerService.findCustomerById(Mockito.anyLong())).thenReturn(Mono.just(customer));
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionCustomersIdCustomerGet(1L, client))
                .expectNextMatches(response -> response.getStatusCode().equals(HttpStatus.OK) && response.getBody().equals(customer))
                .verifyComplete();
    }

    @Test
    public void testBankTransactionCustomersIdCustomerPut() {
        Customer customer = MockData.getCustomer();
        Mockito.when(customerService.updateCustomer(Mockito.anyLong(), Mockito.any(Customer.class))).thenReturn(Mono.just(customer));
        Mono<Customer> customerMono = Mono.just(customer);
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionCustomersIdCustomerPut(1L, customerMono, client))
                .expectNextMatches(response -> response.getStatusCode().equals(HttpStatus.OK))
                .verifyComplete();
    }

    @Test
    public void testBankTransactionCustomersPost() {
        Customer customer = MockData.getCustomer();
        Mockito.when(customerService.saveCustomer(Mockito.any(Customer.class))).thenReturn(Mono.just(customer));
        Mono<Customer> customerMono = Mono.just(customer);
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionCustomersPost(customerMono, client))
                .expectNextMatches(response -> response.getStatusCode().equals(HttpStatus.CREATED) && response.getBody().equals(customer))
                .verifyComplete();
    }
    @Test
    public void testBankTransactionGet() {
        Transaction transaction1 = MockData.getTransaction();
        Transaction transaction2 = MockData.getTransaction();
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        Mockito.when(transactionService.findAllTransactions()).thenReturn(Flux.fromIterable(transactions));

        ServerWebExchange exchange = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionGet(exchange))
                .expectNextMatches(response -> {
                    List<Transaction> transactionList = response.getBody().collectList().block();
                    return response.getStatusCode().equals(HttpStatus.OK) && transactionList.size() == 2 &&
                            transactionList.contains(transaction1) && transactionList.contains(transaction2);
                })
                .verifyComplete();
    }
    @Test
    public void testBankTransactionIdTransactionDelete() {
        Long idTransaction = 1L;
        Mockito.when(transactionService.deleteTransaction(Mockito.anyLong())).thenReturn(Mono.empty());

        ServerWebExchange exchange = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionIdTransactionDelete(idTransaction, exchange))
                .expectNextMatches(response -> {
                    return response.getStatusCode().equals(HttpStatus.OK);
                })
                .verifyComplete();
    }
    @Test
    public void testBankTransactionIdTransactionGet() {
        Transaction transaction = MockData.getTransaction();
        Long id = 1L;
        Mockito.when(transactionService.findTransactionById(id)).thenReturn(Mono.just(transaction));
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionIdTransactionGet(id, client))
                .expectNextCount(1)
                .verifyComplete();
    }



    @Test
    public void testBankTransactionPost() {
        TransactionRequest request = MockData.getTransactionRequest();
        Mockito.when(transactionService.saveTransaction(request)).thenReturn(Mono.empty());
        Mono<TransactionRequest> requestMono = Mono.just(request);
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionPost(requestMono, client))
                .expectNextCount(0)
                .verifyComplete();
    }


    @Test
    public void testBankTransactionAccountStatusGeneratePost() {
        PostAccountStatusRequest request = MockData.getPostAccountStatusRequest();
        PostAccountStatusResponse response = MockData.getPostAccountStatusResponse();
        Mockito.when(transactionService.getReport(request)).thenReturn(Flux.just(response));
        Mono<PostAccountStatusRequest> requestMono = Mono.just(request);
        ServerWebExchange client = MockData.buildServerWebExchange();
        StepVerifier.create(bankTransactionApi.bankTransactionAccountStatusGeneratePost(requestMono, client))
                .expectNextCount(1)
                .verifyComplete();
    }




}


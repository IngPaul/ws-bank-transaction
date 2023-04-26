package com.pichincha.core.service.impl;

import Util.MockData;
import com.pichincha.core.domain.Transaction;
import com.pichincha.core.dto.enums.TransactionTypedEnum;
import com.pichincha.core.mapper.MyMapper;
import com.pichincha.core.mapper.TransactionMapper;
import com.pichincha.core.model.*;
import com.pichincha.core.repositories.AccountRepository;
import com.pichincha.core.repositories.CustomerRepository;
import com.pichincha.core.repositories.TransactionRepository;
import com.pichincha.core.services.TransactionService;
import com.pichincha.core.services.impl.TransactionServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@SpringBootTest
public class TransactionServiceImplTest {

    @Mock
    private MyMapper mapper;

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    private TransactionRequest transactionRequest;
    private com.pichincha.core.domain.Transaction transactionDomain;
    private Account account;
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        transactionRequest = new TransactionRequest();
        transactionRequest.setAccountNumber("123456789");
        transactionRequest.setTransactionTypeId(TransactionRequest.TransactionTypeIdEnum.fromValue(2L));
        transactionRequest.setValue(BigDecimal.TEN);

        transactionDomain = new Transaction();
        transactionDomain.setTransactionId(1L);
        transactionDomain.setAccountId(1L);
        transactionDomain.setTransactionTypeId(2L);
        transactionDomain.setValue(BigDecimal.TEN);
        transactionDomain.setBalance(BigDecimal.ZERO);
        transactionDomain.setDate(OffsetDateTime.now().toLocalDateTime());

        account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber("123456789");
        account.setInitialBalance(BigDecimal.ZERO);
        account.setCustomerId(1L);

        customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("John Doe");
    }

    @Test
    public void testBankTransactionAccountStatusGeneratePost() {
        PostAccountStatusRequest request = MockData.getPostAccountStatusRequest();
        PostAccountStatusResponse response = MockData.getPostAccountStatusResponse();
        Transaction responseRepository= MockData.getTransactionRepository();

        Mockito.when(transactionRepository.generateReport(any(), any(),any())).thenReturn(Flux.just(responseRepository));
        Mockito.when(accountRepository.findByAccountId(anyLong())).thenReturn(Mono.just(MockData.getAccountRepository()));
        Mockito.when(customerRepository.findByCustomerId(anyLong())).thenReturn(Mono.just(MockData.getCustomerRepository()));


        PostAccountStatusRequest requestService= MockData.getPostAccountStatusRequest();
        StepVerifier.create(transactionServiceImpl.getReport(requestService))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void testFindTransactionById() {
        Transaction response=MockData.getTransactionRepository();
        Mockito.when(transactionRepository.findByTransactionId(any())).thenReturn(Mono.just(response));

        StepVerifier.create(transactionServiceImpl.findTransactionById(1L))
                .expectError();
    }

    @Test
    public void testFindTransactionAll() {
        Transaction response=MockData.getTransactionRepository();
        Mockito.when(transactionRepository.findAll()).thenReturn(Flux.just(response));
        Mockito.when(mapper.parse(any(com.pichincha.core.model.Transaction.class),any())).thenReturn(MockData.getTransactionRepository());

        StepVerifier.create(transactionServiceImpl.findAllTransactions())
                .expectError();
    }
}

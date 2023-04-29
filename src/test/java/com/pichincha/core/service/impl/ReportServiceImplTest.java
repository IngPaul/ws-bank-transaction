package com.pichincha.core.service.impl;

import Util.MockData;
import com.pichincha.core.domain.Transaction;
import com.pichincha.core.model.*;
import com.pichincha.core.repositories.AccountRepository;
import com.pichincha.core.repositories.CustomerRepository;
import com.pichincha.core.repositories.TransactionRepository;
import com.pichincha.core.services.impl.ReportServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.time.LocalDateTime;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceImplTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private ReportServiceImpl reportServiceImpl;

    @Test
    public void testBankTransactionAccountStatusGeneratePost() {
        Transaction responseRepository= MockData.getTransactionRepository();
        Mockito.when(transactionRepository.generateReport(anyLong(), any(LocalDateTime.class),any(LocalDateTime.class))).thenReturn(Flux.just(responseRepository));
        Mockito.when(accountRepository.findByAccountId(anyLong())).thenReturn(Mono.just(MockData.getAccountRepository()));
        Mockito.when(customerRepository.findByCustomerId(anyLong())).thenReturn(Mono.just(MockData.getCustomerRepository()));
        PostAccountStatusRequest requestService= MockData.getPostAccountStatusRequest();
        StepVerifier.create(reportServiceImpl.getReport(requestService))
                .expectNextCount(1)
                .verifyComplete();
    }
}

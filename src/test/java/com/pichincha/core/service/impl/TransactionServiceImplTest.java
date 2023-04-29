package com.pichincha.core.service.impl;

import Util.MockData;
import com.pichincha.core.domain.Transaction;
import com.pichincha.core.mapper.MyMapper;
import com.pichincha.core.mapper.TransactionMapper;
import com.pichincha.core.repositories.TransactionRepository;
import com.pichincha.core.services.impl.TransactionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    @Mock
    private MyMapper mapper;
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    @Before
    public void setUp()  {
    }

    @Test
    public void testGetBankTransactionById() {
        Transaction response=MockData.getTransactionRepository();
        Mockito.when(transactionRepository.findByTransactionId(anyLong())).thenReturn(Mono.just(response));
        com.pichincha.core.model.Transaction responseMapper=MockData.getTransaction();
        Mockito.when(mapper.parse(any(),any())).thenReturn(responseMapper);
        StepVerifier.create(transactionServiceImpl.getBankTransactionById(1L))
                .expectNextCount(1).verifyComplete();
    }

    @Test
    public void testFindTransactionAll() {
        Transaction response=MockData.getTransactionRepository();
        Mockito.when(transactionRepository.findAll()).thenReturn(Flux.just(response));
        Mockito.when(mapper.parse(any(),any())).thenReturn(MockData.getTransaction());

        StepVerifier.create(transactionServiceImpl.getBankTransaction())
                .expectNextCount(1)
                .verifyComplete();
    }
}

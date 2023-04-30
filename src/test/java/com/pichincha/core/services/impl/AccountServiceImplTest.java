package com.pichincha.core.services.impl;

import Util.MockData;
import com.pichincha.core.domain.Account;
import com.pichincha.core.mapper.AccountMapper;
import com.pichincha.core.repositories.AccountRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private AccountMapper accountMapper;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void getAccount() {
        Account response= MockData.getAccountRepository();
        Mockito.when(accountRepository.findAll()).thenReturn(Flux.just(response));
        Mockito.when(accountMapper.toAccountModel(any())).thenReturn(MockData.getAccount());
        StepVerifier.create(accountService.getAccount())
                .expectNextCount(1)
                .verifyComplete();

    }
}

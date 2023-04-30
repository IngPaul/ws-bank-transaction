package com.pichincha.core.services.impl;

import com.pichincha.core.error.BankApiException;
import com.pichincha.core.mapper.AccountMapper;
import com.pichincha.core.model.Account;
import com.pichincha.core.repositories.AccountRepository;
import com.pichincha.core.services.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Override
    public Flux<com.pichincha.core.model.Account> getAccount() {
        return accountRepository.findAll()
                .map(c->accountMapper.toAccountModel(c));
    }
    @Override
    public Mono<com.pichincha.core.model.Account> getAccountById(Long accountId) {
        return accountRepository.findByAccountId(accountId)
                .map(accountMapper::toAccountModel)
                .onErrorResume(e->Mono.error(new BankApiException(e.getMessage())));
    }
    @Override
    public Mono<com.pichincha.core.model.Account> postAccount(com.pichincha.core.model.Account account) {
        com.pichincha.core.domain.Account domain = accountMapper.toAccountDomain(account);
        return accountRepository.save(domain)
                .map(accountMapper::toAccountModel)
                .onErrorResume(e->Mono.error(new BankApiException(e.getMessage())));
    }
    @Override
    public  Mono<com.pichincha.core.model.Account> putAccount(Long accountId, Account accountDetails) {
        return accountRepository.findById(accountId)
                .flatMap(accountRepository::save)
                .map(accountMapper::toAccountModel)
                .onErrorResume(e->Mono.error(new BankApiException(e.getMessage())));

    }
    @Override
    public  Mono<Void> deleteAccount(Long accountId) {
        return accountRepository.deleteById(accountId)
                .onErrorResume(e->Mono.error(new BankApiException(e.getMessage())));
    }
}

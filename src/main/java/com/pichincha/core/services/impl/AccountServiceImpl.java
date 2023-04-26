package com.pichincha.core.services.impl;

import com.pichincha.core.error.BankApiException;
import com.pichincha.core.mapper.AccountMapper;
import com.pichincha.core.mapper.MyMapper;
import com.pichincha.core.model.Account;
import com.pichincha.core.repositories.AccountRepository;
import com.pichincha.core.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor

public class AccountServiceImpl implements AccountService {
    @Autowired private AccountMapper mapper;
    @Autowired private AccountRepository accountRepository;
    @Override
    public Flux<com.pichincha.core.model.Account> findAllAccounts() {
        return accountRepository.findAll()
                .map(c->mapper.toAccountModel(c));
    }
    @Override
    public Mono<com.pichincha.core.model.Account> findAccountById(Long accountId) {
        return accountRepository.findByAccountId(accountId)
                .map(r->mapper.toAccountModel(r))
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });
    }
    @Override
    public Mono<com.pichincha.core.model.Account> saveAccount(com.pichincha.core.model.Account account) {
        com.pichincha.core.domain.Account domain = mapper.toAccountDomain(account);
        return accountRepository.save(domain)
                .map(c->mapper.toAccountModel(c))
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });
    }
    @Override
    public  Mono<com.pichincha.core.model.Account> updateAccount(Long accountId, Account accountDetails) {
        return accountRepository.findById(accountId)
                .flatMap(accountDomain->accountRepository.save(accountDomain))
                .map(c->mapper.toAccountModel(c))
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });

    }
    @Override
    public  Mono<Void> deleteAccount(Long accountId) {
        return accountRepository.deleteById(accountId)
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });
    }
}

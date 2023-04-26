package com.pichincha.core.services;

import com.pichincha.core.model.Account;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    public  Flux<Account> findAllAccounts();

    public  Mono<Account> findAccountById(Long accountId);

    public  Mono<Account> saveAccount(Account account);

    public  Mono<Account> updateAccount(Long accountId, Account accountDetails);

    Mono<Void> deleteAccount(Long accountId);
}

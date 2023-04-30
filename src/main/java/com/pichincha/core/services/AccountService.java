package com.pichincha.core.services;

import com.pichincha.core.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    public  Flux<Account> getAccount();
    public  Mono<Account> getAccountById(Long accountId);
    public  Mono<Account> postAccount(Account account);
    public  Mono<Account> putAccount(Long accountId, Account accountDetails);
    Mono<Void> deleteAccount(Long accountId);
}

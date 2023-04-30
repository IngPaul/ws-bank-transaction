package com.pichincha.core.controllers;

import com.pichincha.core.model.Account;
import com.pichincha.core.services.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@Slf4j
@RequiredArgsConstructor
public class AccountApiImpl implements AccountApi {
    private final AccountService accountService;
    
    @Override
    public Mono<ResponseEntity<Void>> deleteAccount(Long idAccount, ServerWebExchange exchange) {
        return accountService.deleteAccount(idAccount)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Flux<Account>>> getAccount(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(accountService.getAccount()));
    }

    @Override
    public Mono<ResponseEntity<Account>> getAccountById(Long idAccount, ServerWebExchange exchange) {
        return accountService.getAccountById(idAccount)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Account>> postAccount(Mono<Account> account, ServerWebExchange exchange) {
        return account.flatMap(accountService::postAccount)
                .map(r->ResponseEntity.status(HttpStatus.CREATED).body(r));
    }

    @Override
    public Mono<ResponseEntity<Void>> putAccount(Long idAccount, Mono<Account> account, ServerWebExchange exchange) {
        return account.flatMap(ac->accountService.putAccount(idAccount,ac))
                .map(r->ResponseEntity.ok().build());
    }
}

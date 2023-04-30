package com.pichincha.core.controllers;


import com.pichincha.core.model.*;
import com.pichincha.core.services.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@Slf4j
@RequiredArgsConstructor
public class BankTransactionApiImpl implements BankTransactionApi {
    private final TransactionService transactionService;

    @Override
    public Mono<ResponseEntity<Void>> deleteBankTransaction(Long idTransaction, ServerWebExchange exchange) {
        return transactionService.deleteTransaction(idTransaction)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Flux<Transaction>>> getBankTransaction(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(transactionService.getBankTransaction()));
    }

    @Override
    public Mono<ResponseEntity<Transaction>> getBankTransactionById(Long idTransaction, ServerWebExchange exchange) {
        return transactionService.getBankTransactionById(idTransaction)
                .doOnNext(r->log.info("find:", r.toString()))
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> postTransaction(Mono<TransactionRequest> transactionRequest, ServerWebExchange exchange) {
        return transactionRequest.flatMap(t->transactionService.postTransaction(t))
                .map(r->ResponseEntity.ok().build());
    }

    @Override
    public Mono<ResponseEntity<Void>> putBankTransaction(Long idTransaction, Mono<Transaction> transaction, ServerWebExchange exchange) {
        return transaction.flatMap(t->transactionService.putBankTransaction(idTransaction,t))
                .map(r->ResponseEntity.ok().build());
    }
}
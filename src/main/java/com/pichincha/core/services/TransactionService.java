package com.pichincha.core.services;

import com.pichincha.core.model.Transaction;
import com.pichincha.core.model.TransactionRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
    public  Flux<Transaction> getBankTransaction();

    public  Mono<Transaction> getBankTransactionById(Long transactionId);

    public  Mono<Transaction> postTransaction(TransactionRequest transaction);

    public  Mono<Transaction> putBankTransaction(Long transactionId, Transaction transactionDetails);

    Mono<Void> deleteTransaction(Long transactionId);


}

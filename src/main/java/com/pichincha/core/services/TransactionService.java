package com.pichincha.core.services;

import com.pichincha.core.model.PostAccountStatusRequest;
import com.pichincha.core.model.PostAccountStatusResponse;
import com.pichincha.core.model.Transaction;
import com.pichincha.core.model.TransactionRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
    public  Flux<Transaction> findAllTransactions();

    public  Mono<Transaction> findTransactionById(Long transactionId);

    public  Mono<Transaction> saveTransaction(TransactionRequest transaction);

    public  Mono<Transaction> updateTransaction(Long transactionId, Transaction transactionDetails);

    Mono<Void> deleteTransaction(Long transactionId);

    Flux<PostAccountStatusResponse> getReport(PostAccountStatusRequest request);
}

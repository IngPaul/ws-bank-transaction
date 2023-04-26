package com.pichincha.core.repositories;


import com.pichincha.core.domain.Transaction;
import com.pichincha.core.dto.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Long> {
    Flux<Transaction> findAll();
    Mono<Transaction> findByTransactionId(Long id);
    @Query("SELECT t FROM Transaction t WHERE t.accountId = :accountId ORDER BY t.transactionId DESC")
    Flux<Transaction> findByAccountId(Long accountId);


    @org.springframework.data.r2dbc.repository.Query("SELECT TR.* FROM transaction as TR\n" +
            "inner join account A \n" +
            "on A.account_id = TR.account_id \n" +
            "inner join customer C\n" +
            "on C.customer_id = A.customer_id \n" +
            "inner join transaction_type TT \n" +
            "on TT.transaction_type_id =TR.transaction_type_id \n" +
            "WHERE date >= :startDate AND date <= :endDate\n" +
            "and C.customer_id = :customerId")
    Flux<Transaction> generateReport(Long customerId, LocalDateTime startDate, LocalDateTime endDate);
}

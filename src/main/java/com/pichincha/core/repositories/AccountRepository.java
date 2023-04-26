package com.pichincha.core.repositories;

import com.pichincha.core.domain.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<Account, Long> {
    Flux<Account> findAll();
    Mono<Account> findByAccountId(Long id);
    Mono<Account> findByAccountNumber(String accountNumber);
}

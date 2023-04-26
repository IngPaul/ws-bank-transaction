package com.pichincha.core.repositories;


import com.pichincha.core.domain.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public interface CustomerRepository  extends ReactiveCrudRepository<Customer, Long> {
    Flux<Customer> findAll();
    Mono<Customer> findByCustomerId(Long id);
    Mono<Customer> save(Customer customer);
}
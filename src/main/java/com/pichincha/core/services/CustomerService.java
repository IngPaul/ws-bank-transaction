package com.pichincha.core.services;

import com.pichincha.core.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<Customer> getCustomer();

    Mono<Customer> getCustomerById(Long customerId);

    Mono<Customer> postCustomer(Customer customer);

    Mono<Customer> putCustomer(Long customerId, Customer customerDetails);

    Mono<Void> deleteCustomer(Long customerId);
}

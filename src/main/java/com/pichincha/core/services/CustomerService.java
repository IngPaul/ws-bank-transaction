package com.pichincha.core.services;

import com.pichincha.core.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<Customer> findAllCustomers();

    Mono<Customer> findCustomerById(Long customerId);

    Mono<Customer> saveCustomer(Customer customer);

    Mono<Customer> updateCustomer(Long customerId, Customer customerDetails);

    Mono<Void> deleteCustomer(Long customerId);
}

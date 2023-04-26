package com.pichincha.core.services.impl;

import com.pichincha.core.error.BankApiException;
import com.pichincha.core.mapper.CustomerMapper;
import com.pichincha.core.mapper.MyMapper;
import com.pichincha.core.model.Customer;
import com.pichincha.core.repositories.CustomerRepository;
import com.pichincha.core.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class CustomerServiceImpl implements CustomerService {
    @Autowired private MyMapper mapper;
    @Autowired private CustomerRepository customerRepository;
    @Override
    public Flux<com.pichincha.core.model.Customer> findAllCustomers() {
        return customerRepository.findAll()
                .map(c->mapper.parse(c, com.pichincha.core.model.Customer.class))
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });
    }
    @Override
    public Mono<com.pichincha.core.model.Customer> findCustomerById(Long customerId) {
        return customerRepository.findByCustomerId(customerId)
                .map(r->mapper.parse(r, com.pichincha.core.model.Customer.class))
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });
    }
    @Override
    public Mono<com.pichincha.core.model.Customer> saveCustomer(com.pichincha.core.model.Customer customer) {
        return customerRepository.save(mapper.parse(customer, com.pichincha.core.domain.Customer.class))
                .map(c->mapper.parse(c, com.pichincha.core.model.Customer.class))
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });
    }
    @Override
    public  Mono<com.pichincha.core.model.Customer> updateCustomer(Long customerId, Customer customerDetails) {
        return customerRepository.findById(customerId)
                .flatMap(customerDomain->customerRepository.save(customerDomain))
                .map(c->mapper.parse(c, com.pichincha.core.model.Customer.class))
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });
    }
    @Override
    public  Mono<Void> deleteCustomer(Long customerId) {
        return customerRepository.deleteById(customerId)
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });
    }
}

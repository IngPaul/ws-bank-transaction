package com.pichincha.core.controllers;

import com.pichincha.core.api.CustomerApi;
import com.pichincha.core.model.Customer;
import com.pichincha.core.services.CustomerService;
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
public class CustomerApiImpl implements CustomerApi {
    private final CustomerService customerService;
    public Mono<ResponseEntity<Void>> deleteCustomer(Long idCustomer, ServerWebExchange exchange) {
        return customerService.deleteCustomer(idCustomer)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Flux<Customer>>> getCustomer(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(customerService.getCustomer()));
    }

    @Override
    public Mono<ResponseEntity<Customer>> getCustomerById(Long idCustomer, ServerWebExchange exchange) {
        return customerService.getCustomerById(idCustomer)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Customer>> postCustomer(Mono<Customer> customer, ServerWebExchange exchange) {
        return customer.flatMap(c->customerService.postCustomer(c))
                .map(c->ResponseEntity.status(HttpStatus.CREATED).body(c));
    }

    @Override
    public Mono<ResponseEntity<Void>> putCustomer(Long idCustomer, Mono<Customer> customer, ServerWebExchange exchange) {
        return customer.flatMap(c->customerService.putCustomer(idCustomer,c))
                .map(r->ResponseEntity.ok().build());
    }
}

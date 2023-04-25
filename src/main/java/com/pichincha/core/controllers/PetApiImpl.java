package com.pichincha.core.controllers;

import com.pichincha.core.api.PetApi;
import com.pichincha.core.model.Pet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
public class PetApiImpl implements PetApi {

    @Override
    public Mono<ResponseEntity<Void>> addPet(Mono<Pet> pet, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().build());
    }

    @Override
    public Mono<ResponseEntity<Flux<Pet>>> getPets(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().build());
    }
}
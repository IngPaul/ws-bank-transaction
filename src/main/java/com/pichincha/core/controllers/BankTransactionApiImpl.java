package com.pichincha.core.controllers;


import com.pichincha.core.api.BankTransactionApi;
import com.pichincha.core.model.*;
import com.pichincha.core.services.AccountService;
import com.pichincha.core.services.CustomerService;
import com.pichincha.core.services.TransactionService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
//@RequiredArgsConstructor
public class BankTransactionApiImpl implements BankTransactionApi {

    @Autowired private AccountService accountService;
    @Autowired private CustomerService customerService;
    @Autowired private TransactionService transactionService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //ACCOUNT
    @Override
        public Mono<ResponseEntity<Flux<Account>>> bankTransactionAccountsGet(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(accountService.findAllAccounts()));
    }

    @Override
    public Mono<ResponseEntity<Void>> bankTransactionAccountsIdAccountDelete(Long idAccount, ServerWebExchange exchange) {
        return accountService.deleteAccount(idAccount)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Account>> bankTransactionAccountsIdAccountGet(Long idAccount, ServerWebExchange exchange) {
        return accountService.findAccountById(idAccount)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> bankTransactionAccountsIdAccountPut(Long idAccount, Mono<Account> account, ServerWebExchange exchange) {
        return account.flatMap(ac->accountService.updateAccount(idAccount,ac))
                                    .map(r->ResponseEntity.ok().build());
    }

    @Override
    public Mono<ResponseEntity<Account>> bankTransactionAccountsPost(Mono<Account> account, ServerWebExchange exchange) {
        return account.flatMap(ac->accountService.saveAccount(ac))
                .map(r->ResponseEntity.status(HttpStatus.CREATED).body(r));
    }

    // CUSTOMER

    @Override
    public Mono<ResponseEntity<Flux<Customer>>> bankTransactionCustomersGet(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(customerService.findAllCustomers()));
    }

    @Override
    public Mono<ResponseEntity<Void>> bankTransactionCustomersIdCustomerDelete(Long idCustomer, ServerWebExchange exchange) {
        return customerService.deleteCustomer(idCustomer)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Customer>> bankTransactionCustomersIdCustomerGet(Long idCustomer, ServerWebExchange exchange) {
        return customerService.findCustomerById(idCustomer)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> bankTransactionCustomersIdCustomerPut(Long idCustomer, Mono<Customer> customer, ServerWebExchange exchange) {
        return customer.flatMap(c->customerService.updateCustomer(idCustomer,c))
                .map(r->ResponseEntity.ok().build());
    }

    @Override
    public Mono<ResponseEntity<Customer>> bankTransactionCustomersPost(Mono<Customer> customer, ServerWebExchange exchange) {
        return customer.flatMap(c->customerService.saveCustomer(c))
                .map(c->ResponseEntity.status(HttpStatus.CREATED).body(c));
    }
    // TRANSACTION
    @Override
    public Mono<ResponseEntity<Flux<Transaction>>> bankTransactionGet(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(transactionService.findAllTransactions()));
    }

    @Override
    public Mono<ResponseEntity<Void>> bankTransactionIdTransactionDelete(Long idTransaction, ServerWebExchange exchange) {
        return transactionService.deleteTransaction(idTransaction)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Transaction>> bankTransactionIdTransactionGet(Long idTransaction, ServerWebExchange exchange) {
        return transactionService.findTransactionById(idTransaction)
                .doOnNext(r->logger.info("find:", r.toString()))
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> bankTransactionIdTransactionPut(Long idTransaction, Mono<Transaction> transaction, ServerWebExchange exchange) {
        return transaction.flatMap(t->transactionService.updateTransaction(idTransaction,t))
                .map(r->ResponseEntity.ok().build());
    }



    @Override
    public Mono<ResponseEntity<Void>> bankTransactionPost(Mono<TransactionRequest> transactionRequest, ServerWebExchange exchange) {
        return transactionRequest.flatMap(t->transactionService.saveTransaction(t))
                .map(r->ResponseEntity.ok().build());
    }

    @Override
    public Mono<ResponseEntity<Flux<PostAccountStatusResponse>>> bankTransactionAccountStatusGeneratePost(Mono<PostAccountStatusRequest> postAccountStatusRequest, ServerWebExchange exchange) {
        return postAccountStatusRequest.map(request -> transactionService.getReport(request))
                .map(ResponseEntity::ok);
    }
}
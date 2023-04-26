package com.pichincha.core.services.impl;


import com.pichincha.core.domain.Account;
import com.pichincha.core.domain.Customer;
import com.pichincha.core.dto.enums.TransactionTypedEnum;
import com.pichincha.core.error.BankApiException;
import com.pichincha.core.mapper.MyMapper;
import com.pichincha.core.mapper.TransactionMapper;
import com.pichincha.core.model.PostAccountStatusRequest;
import com.pichincha.core.model.PostAccountStatusResponse;
import com.pichincha.core.model.Transaction;
import com.pichincha.core.model.TransactionRequest;
import com.pichincha.core.repositories.AccountRepository;
import com.pichincha.core.repositories.CustomerRepository;
import com.pichincha.core.repositories.TransactionRepository;
import com.pichincha.core.services.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static com.pichincha.core.error.BankTransactionError.*;
@Service
@RequiredArgsConstructor

public class TransactionServiceImpl implements TransactionService {
    @Autowired private MyMapper mapper;
    @Autowired private TransactionMapper transactionMapper;
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private AccountRepository accountRepository;
    @Autowired private CustomerRepository customerRepository;
    @Override
    public Flux<com.pichincha.core.model.Transaction> findAllTransactions() {
        return transactionRepository.findAll()
                .map(c->mapper.parse(c, com.pichincha.core.model.Transaction.class))
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });
    }
    @Override
    public Mono<com.pichincha.core.model.Transaction> findTransactionById(Long transactionId) {
        return transactionRepository.findByTransactionId(transactionId)
                .map(r->mapper.parse(r, com.pichincha.core.model.Transaction.class))
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });
    }
    @Override
    public Mono<com.pichincha.core.model.Transaction> saveTransaction(TransactionRequest transaction) {
        return accountRepository.findByAccountNumber(transaction.getAccountNumber())
                .switchIfEmpty(Mono.error(TRANSACTION_ERROR_ACCOUNT_NOT_FOUND))
                .flatMap(account -> transactionRepository.findByAccountId(account.getAccountId())
                            .sort(Comparator.comparing(com.pichincha.core.domain.Transaction::getTransactionId).reversed())
                            .collectList()
                            .map(list->processToGetNewBalance(list, transaction, account))
                            .map(newBalance->transactionMapper.toTransactionDomain(transaction,account,newBalance)))
                .flatMap(toSave->transactionRepository.save(toSave))
                .map(transactionMapper::toTransactionModel)
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });
    }

    private BigDecimal getLastInitialBalance(List<com.pichincha.core.domain.Transaction> list, Account account){
        return (list.size()==0? account.getInitialBalance(): list.get(0).getBalance());
    }
    @SneakyThrows
    private BigDecimal processToGetNewBalance(List<com.pichincha.core.domain.Transaction> list, TransactionRequest transaction, Account account) {
        BigDecimal lastBalance = getLastInitialBalance(list, account);
        if(transaction.getTransactionTypeId().getValue()!=1 && lastBalance.compareTo(transaction.getValue())<0)
            throw new RuntimeException("No hay saldo para debitar");
        if(transaction.getTransactionTypeId().getValue()==1)
            return lastBalance.add(transaction.getValue());
        else return lastBalance.subtract(transaction.getValue());
    }
    @Override
    public  Mono<com.pichincha.core.model.Transaction> updateTransaction(Long transactionId, Transaction transactionDetails) {
        return transactionRepository.findById(transactionId)
                .flatMap(transactionDomain->transactionRepository.save(transactionDomain))
                .map(c->mapper.parse(c, com.pichincha.core.model.Transaction.class))
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });
    }
    @Override
    public  Mono<Void> deleteTransaction(Long transactionId) {
        return transactionRepository.deleteById(transactionId);
    }

    @Override
    public Flux<PostAccountStatusResponse> getReport(PostAccountStatusRequest request) {
        return transactionRepository.generateReport(request.getCustomerId(), request.getDateStart().toLocalDateTime(), request.getDateEnd().toLocalDateTime())
                .flatMap(transaction ->
                    accountRepository.findByAccountId(transaction.getAccountId())
                            .flatMap(account -> customerRepository.findByCustomerId(account.getCustomerId())
                            .map(customer-> buildPostAccountStatusResponse(transaction, account, customer))))
                .onErrorResume(e->{
                    return Mono.error(new BankApiException(e.getMessage()));
                });
    }

    private PostAccountStatusResponse buildPostAccountStatusResponse(com.pichincha.core.domain.Transaction transaction, Account account, Customer customer) {
        return new PostAccountStatusResponse()
                .date(transaction.getDate().toString())
                .customer(customer.getName())
                .accountNumber(account.getAccountNumber())
                .detailTransactionType(TransactionTypedEnum.fromValue(transaction.getTransactionTypeId().longValue()).name())
                .initialValue(calculateInitValue(transaction))
                .value(transaction.getValue())
                .balance(transaction.getBalance());

    }

    private BigDecimal calculateInitValue(com.pichincha.core.domain.Transaction transaction) {
        if(transaction.getTransactionTypeId()==1)
            return transaction.getBalance().subtract(transaction.getValue());
        else
            return transaction.getBalance().add(transaction.getValue());
    }
}

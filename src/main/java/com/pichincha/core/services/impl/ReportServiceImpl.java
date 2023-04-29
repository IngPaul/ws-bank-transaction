package com.pichincha.core.services.impl;

import com.pichincha.core.domain.Account;
import com.pichincha.core.domain.Customer;
import com.pichincha.core.dto.enums.TransactionTypedEnum;
import com.pichincha.core.error.BankApiException;
import com.pichincha.core.mapper.TransactionMapper;
import com.pichincha.core.model.PostAccountStatusRequest;
import com.pichincha.core.model.PostAccountStatusResponse;
import com.pichincha.core.repositories.AccountRepository;
import com.pichincha.core.repositories.CustomerRepository;
import com.pichincha.core.repositories.TransactionRepository;
import com.pichincha.core.services.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    @Override
    public Flux<PostAccountStatusResponse> getReport(PostAccountStatusRequest request) {
        return transactionRepository.generateReport(request.getCustomerId(), request.getDateStart().toLocalDateTime(), request.getDateEnd().toLocalDateTime())
                .flatMap(transaction ->
                        accountRepository.findByAccountId(transaction.getAccountId())
                                .flatMap(account -> customerRepository.findByCustomerId(account.getCustomerId())
                                        .map(customer-> buildPostAccountStatusResponse(transaction, account, customer))))
                .onErrorResume(e->Mono.error(new BankApiException(e.getMessage())));
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

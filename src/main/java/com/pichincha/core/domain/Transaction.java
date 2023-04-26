package com.pichincha.core.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;

@Entity
@Table(name = "transaction")
@Data
@Getter
@Generated
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private LocalDateTime date;

   //@ManyToOne
    //@JoinColumn(name = "transaction_type_id")
    @Column(name = "transaction_type_id")
    private Long transactionTypeId;

    private BigDecimal value;

    private BigDecimal balance;

    @Column(name = "account_id")
    private Long accountId;




    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Transaction() {
    }


    public Transaction(Long transactionId, LocalDateTime date, Long transactionTypeId, BigDecimal value, BigDecimal balance, Long accountId) {
        this.transactionId = transactionId;
        this.date = date;
        this.transactionTypeId = transactionTypeId;
        this.value = value;
        this.balance = balance;
        this.accountId = accountId;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Long transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}


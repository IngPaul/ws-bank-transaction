package com.pichincha.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

public class  Report {
    private LocalDateTime date;
    private String customer;
    private String accountNumber;
    private String detailTransactionType;
    private BigDecimal initialValue;
    private BigDecimal value;
    private BigDecimal balance;



    public Report() {
    }

        public Report(LocalDateTime date, String customer, String accountNumber, String detailTransactionType, BigDecimal initialValue, BigDecimal value, BigDecimal balance) {
        this.date = date;
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.detailTransactionType = detailTransactionType;
        this.initialValue = initialValue;
        this.value = value;
        this.balance = balance;
    }

    public String getDetailTransactionType() {
        return detailTransactionType;
    }

    public void setDetailTransactionType(String detailTransactionType) {
        this.detailTransactionType = detailTransactionType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }



    public BigDecimal getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(BigDecimal initialValue) {
        this.initialValue = initialValue;
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
}

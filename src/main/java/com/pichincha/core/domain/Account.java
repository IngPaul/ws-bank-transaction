package com.pichincha.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long accountId;

  //  @ManyToOne
 //   @JoinColumn(name = "customer")
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "account_number")
    private String accountNumber;

    //@ManyToOne
    //@JoinColumn(name = "account")
    @Column(name = "account_type_id")
    private Long accountTypeId;

    @Column(name = "initial_balance")
    private BigDecimal initialBalance;

    private String status;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

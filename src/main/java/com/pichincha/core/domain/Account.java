package com.pichincha.core.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Data
@Generated
public class Account {

    @Id
    @Column(name = "account_id")
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "account_number")
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;

    @Column(name = "initial_balance")
    private BigDecimal initialBalance;

    private String status;

}

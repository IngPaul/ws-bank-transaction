package com.pichincha.core.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
@Generated
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    private Long transactionId;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    private BigDecimal value;

    private BigDecimal balance;

    // getters y setters

}


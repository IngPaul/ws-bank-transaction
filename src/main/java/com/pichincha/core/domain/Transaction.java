package com.pichincha.core.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
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

}


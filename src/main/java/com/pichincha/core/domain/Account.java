package com.pichincha.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
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
}

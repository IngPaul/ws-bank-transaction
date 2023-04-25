package com.pichincha.core.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_types")
@Data
@Generated
public class TransactionType {

    @Id
    @Column(name = "transaction_type_id")
    private Long transactionTypeId;

    @Column(name = "transaction_type_detail")
    private String transactionTypeDetail;

    // getters y setters

}

package com.pichincha.core.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

import org.springframework.data.relational.core.mapping.Table;
@Entity
@Table(name = "transaction_type")
@Generated
public class TransactionType {

    @Id
    @Column(name = "transaction_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionTypeId;

    @Column(name = "transaction_type_detail")
    private String transactionTypeDetail;


    public Long getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Long transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getTransactionTypeDetail() {
        return transactionTypeDetail;
    }

    public void setTransactionTypeDetail(String transactionTypeDetail) {
        this.transactionTypeDetail = transactionTypeDetail;
    }
}

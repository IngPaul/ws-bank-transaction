package com.pichincha.core.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

import org.springframework.data.relational.core.mapping.Table;
@Entity
@Table(name = "account_type")
@Data
@Generated
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id")
    private Long accountTypeId;

    @Column(name = "account_type_detail")
    private String accountTypeDetail;


    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountTypeDetail() {
        return accountTypeDetail;
    }

    public void setAccountTypeDetail(String accountTypeDetail) {
        this.accountTypeDetail = accountTypeDetail;
    }
}
package com.pichincha.core.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_types")
@Data
@Generated
public class AccountType {

    @Id
    @Column(name = "account_type_id")
    private Long accountTypeId;

    @Column(name = "account_type_detail")
    private String accountTypeDetail;

    // getters y setters

}
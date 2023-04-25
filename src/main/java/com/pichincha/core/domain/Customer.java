package com.pichincha.core.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
@Data
@Generated
public class Customer extends Person {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "password")
    private String password;

    private String status;

    // getters y setters

}

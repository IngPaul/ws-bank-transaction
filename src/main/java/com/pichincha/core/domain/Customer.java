package com.pichincha.core.domain;

import lombok.*;

import javax.persistence.*;

import org.springframework.data.relational.core.mapping.Table;
@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Customer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "password")
    private String password;

    private String status;
}

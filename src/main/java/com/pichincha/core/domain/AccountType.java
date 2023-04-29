package com.pichincha.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;
@Entity
@Table(name = "account_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id")
    private Long accountTypeId;

    @Column(name = "account_type_detail")
    private String accountTypeDetail;

}
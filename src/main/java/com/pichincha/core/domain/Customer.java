package com.pichincha.core.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.relational.core.mapping.Table;
@Entity
@Table(name = "customer")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Customer extends Person {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "password")
    private String password;

    private String status;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

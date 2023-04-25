package com.pichincha.core.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)
@Data
@Generated
public class PersonA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    private String name;

    private String gender;

    private Integer age;

    private String identification;

    private String address;

    private String phone;

    // getters y setters

}

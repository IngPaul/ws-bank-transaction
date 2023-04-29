package com.pichincha.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Person {
    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;
}


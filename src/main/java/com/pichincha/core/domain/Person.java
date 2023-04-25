package com.pichincha.core.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@Generated
public class Person {
    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;

}


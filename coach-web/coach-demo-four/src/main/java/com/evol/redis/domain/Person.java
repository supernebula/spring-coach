package com.evol.redis.domain;

import lombok.Data;

@Data
public class Person {

    String firstName;
    String lastName;


    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

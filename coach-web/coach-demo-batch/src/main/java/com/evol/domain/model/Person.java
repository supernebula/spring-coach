package com.evol.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author admin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String lastName;

    private String firstName;

    @Override
    public String toString() {
        return "firstName: " + firstName + ", lastName: " + lastName;
    }

}

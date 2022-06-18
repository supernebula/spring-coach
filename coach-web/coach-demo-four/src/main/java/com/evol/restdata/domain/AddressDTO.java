package com.evol.restdata.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Data
public class AddressDTO {
    @javax.persistence.Id
    @GeneratedValue
    @Id
    private Long id;

    private final String street, zipCode, city, state;

    public AddressDTO(){
        this.street = null;
        this.zipCode = null;
        this.city = null;
        this.state = null;
    }

    public AddressDTO(String street, String zipCode, String city, String state){
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

    public String toString(){
        return String.format("%s, %s, %s, %s", street, zipCode, city, state);
    }
}

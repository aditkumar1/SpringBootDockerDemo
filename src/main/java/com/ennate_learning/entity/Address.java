package com.ennate_learning.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Address {
    @Id
    private String id;
    String city;

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", street='" + street + '\'' +
                '}';
    }

    String state;
    String street;
    public Address(){
        this.id=UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

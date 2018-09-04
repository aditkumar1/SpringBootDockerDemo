package com.ennate_learning.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class User {
    @Id
    @Column(columnDefinition="VARCHAR(36)")
    private String id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @ManyToOne
    private Address address;
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



    public User(){
        this.id=UUID.randomUUID().toString();
    }
    public User(String firstName, String lastName, String email) {
        this.id=UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public  String toString(){
        StringBuilder addresses=null;
        addresses.append(address);
        return this.firstName+" "+this.lastName+" "+this.email+" lives at "+addresses;
    }
}

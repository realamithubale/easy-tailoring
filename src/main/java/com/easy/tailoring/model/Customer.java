package com.easy.tailoring.model;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer extends AuditModel {
    @Id
    @GeneratedValue(generator = "customer_generator")
    @SequenceGenerator(
            name = "customer_generator",
            sequenceName = "customer_sequence",
            initialValue = 1000
    )
    private Long id;
    private String name;
    private String gender;
    private String mobileNumber;
    @Column(columnDefinition = "text")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
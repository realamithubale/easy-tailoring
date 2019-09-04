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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String mobile1;

    @Column(columnDefinition = "text")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMobile1() { return mobile1; }

    public void setMobile1(String mobile1) { this.mobile1 = mobile1; }

}
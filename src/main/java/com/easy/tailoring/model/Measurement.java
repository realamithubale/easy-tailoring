package com.easy.tailoring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Map;

@Entity
@Table(name = "measurement")
public class Measurement extends AuditModel {
    @Id
    @GeneratedValue(generator = "measurement_generator")
    @SequenceGenerator(
            name = "measurement_generator",
            sequenceName = "measurement_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String remark;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

    private String type;

    private String subType;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "raw_events_custom", joinColumns = @JoinColumn(name =     "raw_event_id"))
    @MapKeyColumn(name = "field_key", length = 50)
    @Column(name = "field_val", length = 100)
    private Map<String,String> properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
package com.iokays.sample.core.adapter.persistence.querydsl.model;

import javax.annotation.processing.Generated;

/**
 * Customer is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class Customer {

    private String customerCode;

    private Integer id;

    private String name;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


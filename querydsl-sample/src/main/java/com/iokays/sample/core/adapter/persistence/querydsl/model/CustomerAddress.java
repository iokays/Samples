package com.iokays.sample.core.adapter.persistence.querydsl.model;

import javax.annotation.processing.Generated;

/**
 * CustomerAddress is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class CustomerAddress {

    private String city;

    private Integer customerId;

    private String detailedAddress;

    private String district;

    private Integer id;

    private String province;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}


package com.iokays.core.domain.customer;


import com.iokays.common.mongodb.AbstractCodePropertyValueConverter;

public class CustomerCodeConverter extends AbstractCodePropertyValueConverter<CustomerCode> {

    @Override
    protected CustomerCode create(String id) {
        return null != id ? new CustomerCode(id) : null;
    }
}

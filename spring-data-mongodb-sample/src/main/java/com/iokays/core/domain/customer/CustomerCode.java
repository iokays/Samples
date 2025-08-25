package com.iokays.core.domain.customer;

import com.iokays.common.mongodb.AbstractCode;
import org.apache.commons.lang3.Validate;

import java.util.UUID;

public class CustomerCode extends AbstractCode {

    private static final String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    private CustomerCode() {
        super();
    }

    public CustomerCode(String id) {
        super(id);
    }

    public static CustomerCode makeCustomerId() {
        return new CustomerCode(UUID.randomUUID().toString());
    }

    /**
     * 验证是否是UUID字符串
     *
     * @param aCode
     */
    @Override
    protected void validateCode(String aCode) {
        Validate.notNull(aCode);
        Validate.isTrue(aCode.matches(UUID_REGEX));
    }
}

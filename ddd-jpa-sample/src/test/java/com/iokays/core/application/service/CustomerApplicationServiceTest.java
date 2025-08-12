package com.iokays.core.application.service;

import com.iokays.core.domain.customer.CustomerInfo;
import com.iokays.core.domain.customer.EmailAddress;
import com.iokays.core.domain.customer.FullName;
import com.iokays.core.domain.customer.Gender;
import com.iokays.core.domain.customer.command.RegisterCustomer;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerApplicationServiceTest {

    @Resource
    private CustomerApplicationService service;

    @Test
    void registerCustomer() {

        final var cmd = RegisterCustomer.issue(FullName.from("孙", "悟空"), Gender.MALE, EmailAddress.from("pengyuanbing@iokays.com"));
        final var customerCode = service.registerCustomer(cmd);
        CustomerInfo customerInfo = service.getCustomerInfo(customerCode);
        Assertions.assertNotNull(customerInfo);
        Assertions.assertEquals(FullName.from("孙", "悟空"), customerInfo.fullName());

        service.updateFullName(customerCode, FullName.from("孙", "行者"));
        customerInfo = service.getCustomerInfo(customerCode);
        Assertions.assertEquals(FullName.from("孙", "行者"), customerInfo.fullName());

        service.deleteCustomer(customerCode);
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.getCustomerInfo(customerCode));

    }
}

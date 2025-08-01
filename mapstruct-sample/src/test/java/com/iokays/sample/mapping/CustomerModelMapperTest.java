package com.iokays.sample.mapping;

import com.iokays.sample.domain.Customer;
import com.iokays.sample.model.CustomerModel;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@SpringBootTest
class CustomerModelMapperTest {

    @Resource
    private CustomerModelMapper mapper;

    @Test
    void test() {
        final var now = LocalDateTime.now();
        final var time = LocalDateTime.of(2025, 7, 30, 19, 36, 0);
        final var customer = Customer.builder()
                .id(Long.MAX_VALUE)
                .name("李白")
                .amount(new BigDecimal("10.891"))
                .registerAt(time)
                .build();
        final CustomerModel customerModel = mapper.toCustomerModel(customer);
        Assertions.assertNotNull(customer.getId());
        Assertions.assertEquals(customer.getId().toString(), customerModel.id());
        Assertions.assertEquals(customer.getName(), customerModel.customerName());
        Assertions.assertEquals(customer.getRegisterAt().toLocalDate(), customerModel.registerAt());
        Assertions.assertEquals("客户系统", customerModel.source());
        Assertions.assertEquals("10.89", customerModel.amount());
        Assertions.assertEquals(time.toLocalDate(), customerModel.registerAt());

        Assertions.assertEquals(1, customerModel.now().compareTo(now));
        Assertions.assertEquals(-1, customerModel.now().compareTo(LocalDateTime.now()));
    }

}
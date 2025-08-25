package com.iokays.core.application.service;

import com.iokays.core.domain.customer.*;
import com.iokays.core.domain.customer.command.RegisterCustomer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class CustomerApplicationServiceTest {

    @Resource
    private CustomerApplicationService applicationService;

    @Resource
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void registerCustomer() {
        final var cmd = RegisterCustomer.issue(FullName.from("孙", "悟空"), Gender.MALE, EmailAddress.from("pengyuanbing@iokays.com"));
        applicationService.registerCustomer(cmd);

        final List<Customer> list = customerRepository.findAll();
        Assertions.assertEquals(1, list.size());

    }
}

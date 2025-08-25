package com.iokays.core.application.service;

import com.iokays.core.domain.customer.Customer;
import com.iokays.core.domain.customer.CustomerRepository;
import com.iokays.core.domain.customer.command.RegisterCustomer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerApplicationService {

    private final CustomerRepository customerRepository;

    @Transactional
    public void registerCustomer(final RegisterCustomer cmd) {
        final var customer = Customer.registerBy(cmd);
        customerRepository.save(customer);
    }

}

package com.iokays.sample.core.service;

import com.iokays.sample.core.domain.Customer;
import com.iokays.sample.core.domain.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class CustomerApplicationService {

    private final CustomerRepository customerRepository;

    @Transactional
    public String create(String name) {
        //忽略了业务逻辑
        final Customer customer = new Customer(name);
        return customerRepository.save(customer).getId();
    }

    @Transactional
    public void update(String id, String name) {
        //忽略了业务逻辑
        final Customer customer = customerRepository.findById(id).orElseThrow();
        customer.update(name);
        customerRepository.save(customer);
    }


    @Transactional
    public void delete(String id) {
        //忽略了业务逻辑
        final Customer customer = customerRepository.findById(id).orElseThrow();
        customerRepository.delete(customer);
    }


}

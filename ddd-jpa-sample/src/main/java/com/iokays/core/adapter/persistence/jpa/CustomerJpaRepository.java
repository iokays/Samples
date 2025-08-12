package com.iokays.core.adapter.persistence.jpa;

import com.iokays.core.domain.customer.Customer;
import com.iokays.core.domain.customer.CustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends CustomerRepository, JpaRepository<Customer, Long> {
}

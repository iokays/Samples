package com.iokays.sample.core.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * 资源库
 */
@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest, String> {

    default LoanRequest newLoanRequest(String customerName, BigDecimal amount) {
        final var entity = new LoanRequest();
        entity.setId(UUID.randomUUID().toString());
        entity.setCustomerName(customerName);
        entity.setAmount(amount);
        return this.save(entity);
    }

}
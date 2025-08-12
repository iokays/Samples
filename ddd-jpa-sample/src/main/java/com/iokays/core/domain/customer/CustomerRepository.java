package com.iokays.core.domain.customer;

import java.util.Optional;

/**
 * 客户仓储, 与客户相关的数据访问接口, 这里不直接使用Spring JPA，
 * 1. 防止接口暴露太多, 泛用
 * 2. 避免与领域对象耦合
 */
public interface CustomerRepository {

    Customer save(Customer entity);

    Optional<Customer> findByEmailAddress(final EmailAddress emailAddress);

    Optional<Customer> findByCustomerCode(final CustomerCode customerCode);

    void delete(Customer entity);

}

package com.iokays.core.application.service;

import com.iokays.common.core.lock.DistributedLock;
import com.iokays.core.domain.customer.*;
import com.iokays.core.domain.customer.command.RegisterCustomer;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerApplicationService {

    private final CustomerRepository customers;

    public CustomerApplicationService(CustomerRepository customers) {
        this.customers = customers;
    }

    @Transactional
    @DistributedLock(value = "customer", key = "#cmd.emailAddress.value") //这里未实现
    public CustomerCode registerCustomer(final RegisterCustomer cmd) {
        final EmailAddress emailAddress = cmd.emailAddress();

        Validate.isTrue(customers.findByEmailAddress(emailAddress).isEmpty(), String.format("给定的电子邮件地址: %s 已经存在", emailAddress));

        final Customer customer = Customer.registerBy(cmd);
        customers.save(customer);

        return customer.customerCode();
    }

    @Transactional
    @DistributedLock(value = "customer", key = "#cmd.emailAddress.value") //这里未实现
    public void updateFullName(CustomerCode customerCode, FullName fullName) {
        customers.findByCustomerCode(customerCode).ifPresent(customer -> {
            customer.fullName(fullName);
            // customers.save(customer); // 因为使用的Spring Data Jpa, 需要使用save触发事件发送, 如果使用 AbstractPersistableAggregateRoot, 则不需要
        });
    }

    @Transactional
    @DistributedLock(value = "customer", key = "#cmd.emailAddress.value") //这里未实现
    public void deleteCustomer(CustomerCode customerCode) {
        customers.findByCustomerCode(customerCode).ifPresent(customers::delete);
    }

    /**
     * 获取客户信息
     * 不要将领域对象返回给外部直接调用, 而是通过领域对象的方法返回领域对象的信息
     *
     * @param customerCode
     * @return
     */
    public CustomerInfo getCustomerInfo(CustomerCode customerCode) {
        return customers.findByCustomerCode(customerCode)
                .map(Customer::info)
                .orElseThrow(() -> new IllegalArgumentException("客户不存在"));
    }

}

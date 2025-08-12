package com.iokays.core.domain.customer;

import com.iokays.common.jpa.AbstractJpaAggregateRoot;
import com.iokays.common.jpa.JpaRegisterDeleteEvent;
import com.iokays.core.domain.customer.command.RegisterCustomer;
import com.iokays.core.domain.customer.event.CustomerDeleted;
import com.iokays.core.domain.customer.event.CustomerFullNameUpdated;
import com.iokays.core.domain.customer.event.CustomerRegistered;
import jakarta.persistence.*;
import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity(name = "t_customer")
public class Customer extends AbstractJpaAggregateRoot<Customer> {

    @AttributeOverride(name = "code", column = @Column(name = "customer_code"))
    private CustomerCode customerCode;

    @AttributeOverride(name = "value", column = @Column(name = "email_address"))
    private EmailAddress emailAddress;

    @Embedded
    @AttributeOverride(name = "firstName", column = @Column(name = "first_name"))
    @AttributeOverride(name = "lastName", column = @Column(name = "last_name"))
    private FullName fullName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDateTime registeredAt;

    protected Customer() {
        super();
    }

    public Customer(FullName fullName, Gender gender, EmailAddress emailAddress) {
        this();
        this.customerCode = CustomerCode.makeCustomerCode();
        this.registeredAt = LocalDateTime.now();

        this.fullName = Validate.notNull(fullName, "name must not be null");
        this.gender = Validate.notNull(gender, "gender must not be null");
        this.emailAddress = Validate.notNull(emailAddress, "emailAddress must not be null");

        this.registerEvent(CustomerRegistered.issue(this.customerCode, this.registeredAt));
    }

    public static Customer registerBy(final RegisterCustomer cmd) {
        Validate.notNull(cmd, "注册客户的命令不能为空");

        return new Customer(
                cmd.fullName(),
                cmd.gender(),
                cmd.emailAddress());
    }

    public void fullName(FullName fullName) {
        this.fullName = fullName;
        this.registerEvent(CustomerFullNameUpdated.issue(this.customerCode, this.fullName, LocalDateTime.now()));
    }

    /**
     * 获取客户信息
     *
     * @return
     */
    public CustomerInfo info() {
        return new CustomerInfo(this.customerCode, this.fullName, this.gender, this.emailAddress);
    }

    @JpaRegisterDeleteEvent //Spring Data JPA 触发机制
    protected void delete() {
        this.registerEvent(CustomerDeleted.issue(this.customerCode));
    }

    public CustomerCode customerCode() {
        return customerCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final Customer customer = (Customer) o;
        return Objects.equals(customerCode, customer.customerCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(customerCode);
    }

}

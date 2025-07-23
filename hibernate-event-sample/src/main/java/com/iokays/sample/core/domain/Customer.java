package com.iokays.sample.core.domain;

import com.iokays.sample.config.AbstractHibernateAggregateRoot;
import com.iokays.sample.core.domain.event.CustomerCreated;
import com.iokays.sample.core.domain.event.CustomerDeleted;
import com.iokays.sample.core.domain.event.CustomerUpdated;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.Validate;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Access(AccessType.FIELD) //使用字段注入，防止贫血
public class Customer extends AbstractHibernateAggregateRoot<Customer> {

    @Id
    @Getter
    @UuidGenerator
    private String id;

    private String name;

    // 供JPA使用
    protected Customer() {
    }

    public Customer(String name) {
        this();
        this.setName(name);
        this.registerEvent(CustomerCreated.builder().name(this.name).dateTime(LocalDateTime.now()).build());
    }

    public void update(final String name) {
        this.setName(name);
        this.registerEvent(CustomerUpdated.builder().id(this.id).name(this.name).dateTime(LocalDateTime.now()).build());
    }

    private void setName(String name) {
        this.name = Validate.notBlank(name, "Name must not be blank.");
    }

    @PreRemove
    private void beforeSave() {
        this.registerEvent(CustomerDeleted.builder().id(this.id).dateTime(LocalDateTime.now()).build());
    }


}

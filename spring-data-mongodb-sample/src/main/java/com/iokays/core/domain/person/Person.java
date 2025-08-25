package com.iokays.core.domain.person;

import com.iokays.common.mongodb.AbstractAggregateRoot;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Person extends AbstractAggregateRoot<Person> {

    @Indexed
    private Integer ssn;

    @DBRef
    private List<Account> accounts;

    protected Person() {
        super();
    }

    public Person(Integer ssn, List<Account> accounts) {
        this();
        this.ssn = ssn;
        this.accounts = accounts;
    }

    @Override
    public boolean sameIdentityAs(Person other) {
        return this.ssn.equals(other.ssn);
    }

}
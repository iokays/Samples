package com.iokays.core.domain.person;

import com.iokays.common.mongodb.IdentifiedValueObject;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account extends IdentifiedValueObject<Account> {

    private Float total;

    protected Account() {
        super();
    }

    public Account(Float total) {
        this();
        this.total = total;
    }

    @Override
    public boolean sameValueAs(Account other) {
        return total.equals(other.total);
    }
}

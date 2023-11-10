package com.iokays.boot.mysql.jpa.specification.predicate;

import java.math.BigDecimal;
import java.util.function.Predicate;

public abstract class ActivitySpecification {

    public boolean testCreate(final String name, final BigDecimal balance) {
        Predicate<String> nameLeng200 = s -> s.length() > 200;

        return nameLeng200.test(name) && balance.compareTo(BigDecimal.ZERO) > 0;
    }

    private static Predicate<String> nameLeng200() {
        return (String s) -> s.length() > 200;
    }


}




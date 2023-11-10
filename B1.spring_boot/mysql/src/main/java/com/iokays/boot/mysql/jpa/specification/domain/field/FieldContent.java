package com.iokays.boot.mysql.jpa.specification.domain.field;

import com.iokays.boot.mysql.jpa.specification.domain.SpecificationBaseEntity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotNull;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FieldContent<T> extends SpecificationBaseEntity {

    @NotNull
    protected T value;

    protected T anotherValue;

    public T getValue() {
        return value;
    }

    public T getAnotherValue() {
        return anotherValue;
    }

    @Override
    public String toString() {
        return anotherValue != null ? value + " AND " + anotherValue : value.toString();
    }
}

package com.iokays.core.domain.customer;

import com.iokays.common.core.domain.ValueObject;
import jakarta.persistence.Embeddable;

import java.util.Objects;

/**
 * 客户的姓名
 * 不可变对象
 *
 * @param firstName 名
 * @param lastName  姓
 */
@Embeddable
public record FullName(String firstName, String lastName) implements ValueObject<FullName> {

    public FullName {
        Objects.requireNonNull(firstName, "名不能为空");
        Objects.requireNonNull(lastName, "姓不能为空");
    }

    /**
     * 创建客户的姓名 {@link FullName}
     *
     * @param firstName 名
     * @param lastName  姓
     * @return 客户全名 {@link FullName}
     */
    public static FullName from(String firstName, String lastName) {
        return new FullName(firstName, lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final FullName fullName = (FullName) o;
        return Objects.equals(lastName, fullName.lastName) && Objects.equals(firstName, fullName.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}

package com.iokays.common.core.domain;

import java.io.Serializable;

/**
 * 实体
 */
public interface Entity<T> extends Serializable {

    /**
     * 实体通过标识比较，而不是通过属性比较。
     *
     * @param other 另一个实体。
     * @return 如果标识相同，则返回true，而不管其他属性如何。
     */
    default boolean sameIdentityAs(T other) {
        return this.equals(other);
    }

}

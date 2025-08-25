package com.iokays.common.core.domain;

import java.io.Serializable;

/**
 * 值对象
 */
public interface ValueObject<T> extends Serializable {

    /**
     * 值对象通过属性值比较，它们没有标识。
     *
     * @param other 另一个值对象。
     * @return 如果给定的值对象和这个值对象的属性相同，则返回<code>true</code>。
     */
    default boolean sameValueAs(T other) {
        return this.equals(other);
    }

}

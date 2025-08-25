package com.iokays.common.core.validate;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public interface AssertionConcern {

    default void assertTrue(boolean expression, Supplier<? extends RuntimeException> supplier) {
        if (!expression) {
            throw supplier.get();
        }
    }

    default <T> T assertNotNull(T object, Supplier<? extends RuntimeException> supplier) {
        this.assertTrue(Objects.nonNull(object), supplier);
        return object;
    }

    default <T> T[] assertNotEmpty(T[] array, Supplier<? extends RuntimeException> supplier) {
        this.assertTrue(ArrayUtils.isNotEmpty(array), supplier);
        return array;

    }

    default <T extends Collection<?>> T assertNotEmpty(T collection, Supplier<? extends RuntimeException> supplier) {
        this.assertTrue(CollectionUtils.isNotEmpty(collection), supplier);
        return collection;
    }

    default <T extends Map<?, ?>> T assertNotEmpty(T map, Supplier<? extends RuntimeException> supplier) {
        this.assertTrue(MapUtils.isNotEmpty(map), supplier);
        return map;
    }

    default <T extends CharSequence> T assertNotEmpty(T chars, Supplier<? extends RuntimeException> supplier) {
        this.assertTrue(StringUtils.isNotEmpty(chars), supplier);
        return chars;
    }

    default <T extends CharSequence> T notBlank(T chars, Supplier<? extends RuntimeException> supplier) {
        this.assertTrue(StringUtils.isNotBlank(chars), supplier);
        return chars;
    }

    default <T> T[] noNullElements(T[] array, Supplier<? extends RuntimeException> supplier) {
        this.assertTrue(ArrayUtils.isNotEmpty(array), supplier);
        return array;
    }

    default void isInstanceOf(Class<?> type, Object obj, Supplier<? extends RuntimeException> supplier) {
        assertTrue(type.isInstance(obj), supplier);
    }


    default void isAssignableFrom(Class<?> superType, Class<?> type, Supplier<? extends RuntimeException> supplier) {
        assertTrue(superType.isAssignableFrom(type), supplier);
    }

}

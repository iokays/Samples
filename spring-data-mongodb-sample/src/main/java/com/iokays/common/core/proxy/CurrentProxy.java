package com.iokays.common.core.proxy;

/**
 * 该代理不建议设置为公开的。
 *
 * @param <T>
 */
@Deprecated
public interface CurrentProxy<T> {

    T _this();

}

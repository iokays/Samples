package com.iokays.core.application.service.error;

/**
 * 客户不存在异常
 */
public class CustomerNotFound extends RuntimeException {

    /**
     * 默认构造函数
     *
     * @param message {@link String}异常消息
     */
    public CustomerNotFound(final String message) {
        super(message);
    }
}

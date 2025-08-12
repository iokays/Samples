package com.iokays.core.application.service.error;


/**
 * 客户已存在异常
 */
public class CustomerAlreadyExist extends RuntimeException {

    /**
     * 默认构造函数
     *
     * @param message {@link String}异常消息
     */
    public CustomerAlreadyExist(final String message) {
        super(message);
    }

}

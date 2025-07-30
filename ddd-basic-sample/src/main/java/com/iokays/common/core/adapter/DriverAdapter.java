package com.iokays.common.core.adapter;

import java.lang.annotation.*;

/**
 * 主适配器（别名Driving Adapter）代表用户如何使用应用，
 * 从技术上来说，它们接收用户输入，调用端口并返回输出。
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DriverAdapter {
}

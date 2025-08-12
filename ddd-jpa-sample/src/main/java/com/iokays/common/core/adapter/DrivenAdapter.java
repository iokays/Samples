package com.iokays.common.core.adapter;

import java.lang.annotation.*;

/**
 * 次适配器（别名Driven Adapter）
 * 实现应用的出口端口，向外部工具执行操作
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DrivenAdapter {
}

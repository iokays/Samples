package com.iokays.common.core.update;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记方法的参数是可变的，即可以在方法内部被修改属性值。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Mutable {
    // 这里可以添加一些属性，例如描述、版本等，根据需要自定义
    String value() default "";
}


package com.iokays.core.sample;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * NullTest
 */
public class IfNullTest {

    /**
     * Optional 是 Java 8 引入的一个容器类，用于明确表示一个值可能为 null ，并提供了一系列方法来优雅地处理这种情况。
     */
    public void optional() {
        final Optional<String> optional = Optional.ofNullable(null);
        optional.ifPresent(v -> {
            // do something
        });
    }

     /**
     * 卫语句
     * 是一种编程技巧，用于在方法开始时检查前置条件，提前返回或抛出异常，从而减少嵌套层次和提高代码可读性。
     *
     * 优势
     * 减少嵌套层次：避免"箭头代码"（深度嵌套）
     * 提前失败：快速发现和处理错误情况
     * 清晰的主逻辑: 核心业务逻辑不被错误处理淹没
     * 可维护性：更容易添加新的前置条件检查
     * 性能优化：提前终止无效操作
     */
    public void ifGuardClauses(Object o) {
        // old start
        if (o != null) {
            // do something
        }
        // end


        // new start
        if (o == null) {
            return;
        }
        // do something
        // end
    }


    /**
     * 提前断言
     * 优势: 明确表达意图, 减少嵌套层次, 提前发现错误
     * @param o
     */
    public void ifAssert(Object o) {
        // old start
        if (o == null) {
            throw new NullPointerException();
        }
        // do something
        //end

        // new start
        Objects.requireNonNull(o);
        // do something
        // end
    }


    /**
     * 集合处理
     */
    public void CollectionIfNull() {
        final List<String> list = List.of("1", "2", "3");

        // old start
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(v -> {
                // do something
            });
        }
        // end

        // new start 使用集合处理工具类,减少Null的判断.
        CollectionUtils.emptyIfNull(list).forEach(v -> {
            // do something
        });
        // end
    }

}


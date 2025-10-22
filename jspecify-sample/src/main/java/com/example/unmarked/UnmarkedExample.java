// Path: D:/Samples/jspecify-sample/src/main/java/com/example/unmarked/UnmarkedExample.java
package com.example.unmarked;

import org.jspecify.annotations.Nullable; // New import

public class UnmarkedExample {

    /**
     * 这个方法返回一个 String，但没有 nullness 注解。
     * 因为包是 @NullUnmarked，所以这个 String 的 nullness 是 unspecified。
     * 它可以是 null，也可以是非 null，Checker Framework 不会强制检查。
     */
    public @Nullable String getValue() { // Added @Nullable
        return null; // 在 @NullUnmarked 包中，返回 null 是允许的，不会有编译错误
    }

    /**
     * 这个方法接受一个 String 参数，其 nullness 也是 unspecified。
     */
    public void printValue(@Nullable String value) { // Added @Nullable
        System.out.println("Unmarked value: " + value);
    }
}
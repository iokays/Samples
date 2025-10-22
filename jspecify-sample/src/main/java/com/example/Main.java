// Path: D:/Samples/jspecify-sample/src/main/java/com/example/Main.java
package com.example;

import com.example.unspecified.LegacyCode;
import com.example.unmarked.UnmarkedExample; // New import
import org.jspecify.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        demonstratePerson();
        demonstrateLegacyInteraction();
        demonstrateUnmarked(); // New call
    }

    /**
     * 演示 Person 类的基本 @NonNull 和 @Nullable 用法。
     */
    private static void demonstratePerson() {
        System.out.println("--- Person 示例 ---");
        // 这是一个合法的用法，因为 middleName 是 @Nullable
        Person person1 = new Person("John Doe", null);
        System.out.println("Person 1 (middleName is null): " + person1.getName());

        // 这也是一个合法的person对象
        Person person2 = new Person("John Fitzgerald", "Doe");
        System.out.println("Person 2 (middleName is not null): " + person2.getName());
        System.out.println();
    }

    /**
     * 演示与未经 JSpecify 注解的旧代码交互时的情况。
     */
    private static void demonstrateLegacyInteraction() {
        System.out.println("--- LegacyCode 交互示例 ---");
        LegacyCode legacy = new LegacyCode();
        // 下面的代码在编译时会产生一个警告（除非被抑制），因为它将一个 nullness-unspecified 的值
        // 赋给了 Person 构造函数中一个需要 @NonNull 的参数。
        // 这展示了 JSpecify 如何帮助我们发现与旧代码交互时的潜在风险。
        try {
            Person personFromLegacy = legacy.createPersonWithUnspecifiedName();
            // 如果没有抛出异常，我们打印名字长度，但这会触发 NPE
            System.out.println("尝试访问 legacy person 的 name 长度...");
            System.out.println(personFromLegacy.getNameLength());
        } catch (NullPointerException e) {
            System.out.println("从 LegacyCode 创建 Person 并访问其 name 时捕获到预期的 NullPointerException。");
        }
    }

    /**
     * 演示 @NullUnmarked 包的行为。
     */
    private static void demonstrateUnmarked() {
        System.out.println("\n--- @NullUnmarked 示例 ---");
        UnmarkedExample unmarked = new UnmarkedExample();
        String value = unmarked.getValue(); // 允许返回 null，不会有编译错误
        unmarked.printValue(value); // 允许传入 null，不会有编译错误
        System.out.println("在 @NullUnmarked 包中，nullness 是 unspecified，不会强制检查。");
    }
}

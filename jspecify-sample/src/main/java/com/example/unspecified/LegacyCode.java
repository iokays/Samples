// Path: D:/Samples/jspecify-sample/src/main/java/com/example/unspecified/LegacyCode.java
package com.example.unspecified;

// 这个包没有 @NullMarked 注解，所以所有类型都是 "nullness-unspecified"。
// 这是为了模拟与尚未迁移到 JSpecify 的旧代码进行交互。

import com.example.Person;
import org.jspecify.annotations.Nullable;

public class LegacyCode {

    /**
     * 这个方法返回一个 String，但没有 nullness 注解。
     * JSpecify 工具会将其视为 nullness-unspecified。
     */
    public @Nullable String getName() {
        // 在实际的旧代码中，这可能返回 null
        return null;
    }

    /**
     * 这个方法接收一个 Person 对象，并尝试从未经检查的来源获取名字并设置它。
     */
    public Person createPersonWithUnspecifiedName() {
        String unspecifiedName = getName();

        // 当我们将一个 nullness-unspecified 的值传递给一个需要 @NonNull 的参数时，
        // Checker Framework 应该会发出警告。
        // 这正是 JSpecify 帮助我们识别潜在 bug 的地方。
        return new Person(unspecifiedName, "Legacy");
    }
}

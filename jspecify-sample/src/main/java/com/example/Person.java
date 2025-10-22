// Path: D:/Samples/jspecify-sample/src/main/java/com/example/Person.java
package com.example;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class Person {

    // 因为包已标记为 @NullMarked，此字段默认为 @NonNull
    private final String name;
    // 这个字段必须显式标记为 @Nullable，以允许其值为 null
    private final @Nullable String middleName;

    /**
     * 构造函数
     * @param name 名字，默认为 @NonNull
     * @param middleName 中间名，显式标记为 @Nullable
     */
    public Person(String name, @Nullable String middleName) {
        this.name = name;
        this.middleName = middleName;
    }

    // 返回类型默认为 @NonNull
    public String getName() {
        return name;
    }

    // 返回类型必须显式标记为 @Nullable
    public @Nullable String getMiddleName() {
        return middleName;
    }

    public int getNameLength() {
        return name.length();
    }

    public int getMiddleNameLength() {
        // 我们必须检查 middleName 是否为 null，因为它是 @Nullable
        if (middleName != null) {
            return middleName.length();
        }
        return 0;
    }
}

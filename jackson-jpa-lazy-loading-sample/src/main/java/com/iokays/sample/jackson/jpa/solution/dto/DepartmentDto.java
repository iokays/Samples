// Path: Samples/jackson-jpa-lazy-loading-sample/src/main/java/com/iokays/sample/jackson/jpa/solution/dto/DepartmentDto.java
package com.iokays.sample.jackson.jpa.solution.dto;

/**
 * 解决方案3: DTO投影
 * <p>
 * 这是一个只包含我们想要序列化字段的简单数据传输对象（DTO）。
 * 使用Java 17的record特性可以非常简洁地定义它。
 */
public record DepartmentDto(Long id, String name) {
}

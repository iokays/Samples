// Path: Samples/java-json-deserialization-sample/src/main/java/com/iokays/json/deserialization/sample/JacksonDeserializer.java
package com.iokays.json.deserialization.sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

/**
 * 使用 Jackson 库将 JSON 字符串反序列化为 Map。
 * Jackson 是一个功能强大且广泛使用的库，它能很好地保留值的原始数据类型。
 */
public class JacksonDeserializer {
    private final ObjectMapper objectMapper;

    public JacksonDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * 执行反序列化操作。
     * @param jsonString 输入的JSON字符串
     * @return 一个 Map<String, Object>，其中值的类型被正确保留
     */
    public Map<String, Object> deserialize(String jsonString) {
        try {
            TypeReference<Map<String, Object>> typeRef = new TypeReference<>() {};
            return objectMapper.readValue(jsonString, typeRef);
        } catch (Exception e) {
            throw new RuntimeException("使用 Jackson 反序列化JSON失败: " + e.getMessage(), e);
        }
    }
}

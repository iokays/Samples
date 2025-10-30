// Path: Samples/java-json-deserialization-sample/src/main/java/com/iokays/json/deserialization/sample/GsonDeserializer.java
package com.iokays.json.deserialization.sample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 使用 Google 的 Gson 库将 JSON 字符串反序列化为 Map。
 * Gson 轻量且易于使用，但默认情况下会将所有数字类型视为 Double。
 */
public class GsonDeserializer {
    private final Gson gson;

    public GsonDeserializer() {
        this.gson = new Gson();
    }

    /**
     * 执行反序列化操作。
     * @param jsonString 输入的JSON字符串
     * @return 一个 Map<String, Object>，其中数字默认为 Double 类型
     */
    public Map<String, Object> deserialize(String jsonString) {
        try {
            Type type = new TypeToken<Map<String, Object>>() {}.getType();
            return gson.fromJson(jsonString, type);
        } catch (Exception e) {
            throw new RuntimeException("使用 Gson 反序列化JSON失败: " + e.getMessage(), e);
        }
    }
}

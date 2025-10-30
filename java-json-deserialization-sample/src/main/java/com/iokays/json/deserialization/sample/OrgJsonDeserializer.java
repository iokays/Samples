// Path: Samples/java-json-deserialization-sample/src/main/java/com/iokays/json/deserialization/sample/OrgJsonDeserializer.java
package com.iokays.json.deserialization.sample;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用 org.json 库将 JSON 字符串反序列化为 Map。
 * 这是一个轻量级的库，它会将整数保留为 Integer，将小数保留为 BigDecimal。
 */
public class OrgJsonDeserializer {

    /**
     * 执行反序列化操作。
     * @param jsonString 输入的JSON字符串
     * @return 一个 Map<String, Object>，其中保留了数字类型的精度
     */
    public Map<String, Object> deserialize(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            Map<String, Object> result = new HashMap<>();

            for (String key : jsonObject.keySet()) {
                Object value = jsonObject.get(key);
                if (value instanceof JSONArray) {
                    value = ((JSONArray) value).toList();
                } else if (value instanceof JSONObject) {
                    value = ((JSONObject) value).toMap();
                }
                result.put(key, value);
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException("使用 org.json 反序列化JSON失败: " + e.getMessage(), e);
        }
    }
}

// Path: Samples/java-json-deserialization-sample/src/main/java/com/iokays/json/deserialization/sample/JsonPDeserializer.java
package com.iokays.json.deserialization.sample;

import jakarta.json.*;
import jakarta.json.stream.JsonParsingException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用 JSON-P (Java API for JSON Processing) 将 JSON 字符串反序列化为 Map。
 * JSON-P 是 Java 的标准API，它提供了对解析过程的完全控制，但代码相对冗长。
 */
public class JsonPDeserializer {

    /**
     * 执行反序列化操作。
     * @param jsonString 输入的JSON字符串
     * @return 一个 Map<String, Object>，其中类型被精确控制
     */
    public Map<String, Object> deserialize(String jsonString) {
        try (JsonReader reader = Json.createReader(new StringReader(jsonString))) {
            JsonObject jsonObject = reader.readObject();
            return convertJsonToMap(jsonObject);
        } catch (JsonParsingException e) {
            throw new RuntimeException("使用 JSON-P 反序列化JSON失败: " + e.getMessage(), e);
        }
    }

    private Map<String, Object> convertJsonToMap(JsonObject jsonObject) {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, JsonValue> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonValue value = entry.getValue();
            result.put(key, convertJsonValue(value));
        }
        return result;
    }

    private Object convertJsonValue(JsonValue jsonValue) {
        switch (jsonValue.getValueType()) {
            case STRING:
                return ((JsonString) jsonValue).getString();
            case NUMBER:
                JsonNumber num = (JsonNumber) jsonValue;
                return num.isIntegral() ? num.longValue() : num.doubleValue();
            case TRUE:
                return true;
            case FALSE:
                return false;
            case NULL:
                return null;
            case ARRAY:
                return convertJsonArray((JsonArray) jsonValue);
            case OBJECT:
                return convertJsonToMap((JsonObject) jsonValue);
            default:
                return jsonValue.toString();
        }
    }

    private List<Object> convertJsonArray(JsonArray jsonArray) {
        List<Object> list = new ArrayList<>();
        for (JsonValue value : jsonArray) {
            list.add(convertJsonValue(value));
        }
        return list;
    }
}

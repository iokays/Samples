// Path: Samples/java-json-deserialization-sample/src/test/java/com/iokays/json/deserialization/sample/GsonDeserializerTest.java
package com.iokays.json.deserialization.sample;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * GsonDeserializer 的单元测试。
 * 验证 Gson 的反序列化行为，特别是对数字类型的处理。
 */
class GsonDeserializerTest {

    private final String json = """
        {
            "name": "孙悟空",
            "age": 500,
            "isActive": true,
            "salary": 99999.99,
            "hobbies": ["筋斗云", "七十二变"],
            "address": {
                "street": "花果山",
                "city": "水帘洞"
            }
        }
        """;

    @Test
    void testDeserialize() {
        GsonDeserializer deserializer = new GsonDeserializer();
        Map<String, Object> result = deserializer.deserialize(json);

        assertNotNull(result, "结果Map不应为null");
        assertEquals("孙悟空", result.get("name"), "name 应该是 String 类型");
        // 注意：Gson 默认将所有数字视为 Double
        assertEquals(500.0, result.get("age"), "age 应该是 Double 类型 (Gson默认行为)");
        assertEquals(true, result.get("isActive"), "isActive 应该是 Boolean 类型");
        assertEquals(99999.99, result.get("salary"), "salary 应该是 Double 类型");

        Object hobbies = result.get("hobbies");
        assertTrue(hobbies instanceof ArrayList, "hobbies 应该是 ArrayList 类型");
        assertEquals(2, ((ArrayList<?>) hobbies).size(), "hobbies 列表应包含2个元素");
        assertEquals("七十二变", ((ArrayList<?>) hobbies).get(1));
        
        Object address = result.get("address");
        assertTrue(address instanceof Map, "address 应该是 Map 类型");
        assertEquals("水帘洞", ((Map<?, ?>) address).get("city"));
    }
}

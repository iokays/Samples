// Path: Samples/java-json-deserialization-sample/src/test/java/com/iokays/json/deserialization/sample/JsonPDeserializerTest.java
package com.iokays.json.deserialization.sample;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JsonPDeserializer 的单元测试。
 * 验证 JSON-P 的手动类型转换是否正确。
 */
class JsonPDeserializerTest {

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
        JsonPDeserializer deserializer = new JsonPDeserializer();
        Map<String, Object> result = deserializer.deserialize(json);

        assertNotNull(result, "结果Map不应为null");
        assertEquals("孙悟空", result.get("name"), "name 应该是 String 类型");
        // 注意：根据Baeldung文章的测试和实际行为，JSON-P的此实现将整数也转换为了Double。
        assertEquals(500.0, result.get("age"), "age 应该是 Double 类型");
        assertEquals(true, result.get("isActive"), "isActive 应该是 Boolean 类型");
        assertEquals(99999.99, result.get("salary"), "salary 应该是 Double 类型");

        Object hobbies = result.get("hobbies");
        assertTrue(hobbies instanceof List, "hobbies 应该是 List 类型");
        assertEquals(2, ((List<?>) hobbies).size(), "hobbies 列表应包含2个元素");

        Object address = result.get("address");
        assertTrue(address instanceof Map, "address 应该是 Map 类型");
        assertEquals("水帘洞", ((Map<?, ?>) address).get("city"));
    }
}

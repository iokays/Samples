# Java JSON 反序列化示例 (java-json-deserialization-sample)

本项目旨在演示和比较四种主流的Java库在处理JSON反序列化时的不同行为，特别是将一个JSON字符串转换为 `Map<String, Object>` 并同时保持值原始数据类型的能力。

## 核心问题

当我们将一个JSON对象反序列化为一个 `Map<String, Object>` 时，我们希望Map中的值（如数字、布尔值、数组）能被正确地转换为Java中对应的类型（`Integer`, `Boolean`, `List`），而不仅仅是 `String`。本项目通过四个示例展示了不同库如何实现这一目标。

## 示例JSON

所有示例都使用以下JSON字符串作为输入：

```json
{
    "name": "John",
    "age": 30,
    "isActive": true,
    "salary": 50000.75,
    "hobbies": ["reading", "coding"],
    "address": {
        "street": "123 Main St",
        "city": "New York"
    }
}
```

## 各库实现对比

项目中包含了以下四个库的实现和单元测试：

### 1. Jackson (`JacksonDeserializer.java`)

*   **依赖**: `com.fasterxml.jackson.core:jackson-databind`
*   **行为**: 提供了最优秀和最可靠的类型保持能力。整数 (`age`) 被正确解析为 `Integer`，浮点数 (`salary`) 为 `Double`，布尔值为 `Boolean`，数组为 `ArrayList`。
*   **结论**: 是生产环境和处理复杂JSON结构的首选。

### 2. Gson (`GsonDeserializer.java`)

*   **依赖**: `com.google.code.gson:gson`
*   **行为**: 轻量且易于使用。但其主要特点是，**默认将所有数字类型（无论整数还是浮点数）都解析为 `Double`**。因此，`age` (30) 在结果中会变成 `30.0`。
*   **结论**: 适用于简单的JSON处理或对数字类型不敏感的场景。

### 3. org.json (`OrgJsonDeserializer.java`)

*   **依赖**: `org.json:json`
*   **行为**: 另一个轻量级选择。它在处理数字时非常精确：整数 (`age`) 被解析为 `Integer`，而为了保证精度，**浮点数 (`salary`) 被解析为 `BigDecimal`**。
*   **结论**: 当需要精确处理小数（如金融计算）且不希望引入重型库时，是一个不错的选择。

### 4. JSON-P (`JsonPDeserializer.java`)

*   **依赖**: `jakarta.json:jakarta.json-api` 和 `org.eclipse.parsson:parsson`
*   **行为**: 作为Java官方标准，它提供了最底层的控制。在我们的实现中，通过 `isIntegral()` 判断，我们发现其实现将整数解析为了 `Double`。这提供了精确的类型控制，但代码也最为冗长。
*   **结论**: 适用于需要严格遵守Java标准或需要对解析过程进行精细控制的场景。

## 如何运行

你可以直接运行项目中的单元测试来验证每个库的行为：

```shell
# 在项目根目录执行
./gradlew build
```

所有测试都已通过，你可以在 `build/reports/tests/test/index.html` 查看详细的测试报告。
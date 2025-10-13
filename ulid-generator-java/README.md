# Java ULID (Universally Unique Lexicographically Sortable Identifier) 生成器示例

这是一个基于Java 25和 `ulid-creator` 库实现的ULID生成器示例项目。

## 核心功能

- **标准ULID生成**: 演示如何生成一个标准的ULID，并获取其多种表示形式（字符串、UUID）。
- **单调ULID生成**: 演示如何生成在同一毫秒内严格递增的ULID，适用于需要严格排序的场景。
- **可排序性验证**: 展示了ULID的核心特性——其字符串表示是按时间顺序自然排序的。

## 如何运行

你可以通过以下Gradle命令来运行此项目：

```bash
./gradlew run
```
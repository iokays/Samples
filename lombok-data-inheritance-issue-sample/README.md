# Lombok @Data 与继承：解决 `final` 字段导致的构造函数错误

本项目旨在演示一个在使用 [Lombok](https://projectlombok.org/) 时常见的继承问题，并提供多种解决方案。该问题源于 Baeldung 的一篇文章：[Lombok @Data and Final Fields: Solving the “Default Constructor in Base Class” Error](https://www.baeldung.com/lombok-data-final-fields-default-constructor)。

## 1. 问题描述

当一个使用 `@Data` 注解的子类继承一个包含 `final` 字段但**没有**无参构造函数的抽象基类时，会触发以下编译错误：

```
lombok needs a default constructor in the base class
```

这是因为 `@Data` 会尝试生成一个无参构造函数，而该构造函数需要调用父类的无参构造函数 `super()`。如果父类不存在这样的构造函数，Java 的继承规则就被打破了。

本项目通过一个 `BaseEntity`（基类，包含 `final String createdBy`）和一个 `User`（子类）来复现此问题。

## 2. 项目结构与解决方案

为了清晰地展示问题和解决方案，本项目代码组织在不同的包下：

```
src/main/java/com/iokays/sample/lombok
├── problem         # 包含导致编译错误的代码（文件已禁用）
├── solution
│   ├── audit       # 模拟审计字段的场景（原JPA场景简化版）
│   ├── explicit    # 方案2: 提供显式的无参构造函数
│   ├── force       # 方案1: 使用 @NoArgsConstructor(force = true)
│   └── targeted    # 方案3: 使用 @Getter/@Setter 等精确注解
```

### 方案一: `@NoArgsConstructor(force = true)`

- **位置**: `solution.force`
- **做法**: 在 `BaseEntity` 上添加 `@NoArgsConstructor(force = true)`。
- **效果**: Lombok 会强制生成一个无参构造函数，并将 `final` 字段 `createdBy` 初始化为 `null`。
- **优点**: 简单快捷。
- **缺点**: 破坏了 `final` 字段的不可变性和非空约束，可能导致后续的 `NullPointerException`。这是一种有风险的“妥协”。

### 方案二: 显式提供默认构造函数 (推荐)

- **位置**: `solution.explicit`
- **做法**: 在 `BaseEntity` 中手动添加一个 `protected` 的无参构造函数，并为 `createdBy` 字段赋予一个有意义的默认值（例如 `"system"`）。
- **效果**: `User` 类中的 `@Data` 注解可以顺利调用到父类的无参构造函数，同时保证了 `createdBy` 字段始终有一个有效值。
- **优点**: 安全、可靠，符合面向对象的设计原则。
- **缺点**: 需要手动编写构造函数。

### 方案三: 使用精确注解代替 `@Data`

- **位置**: `solution.targeted`
- **做法**: 在 `User` 类上，放弃使用 `@Data`，改为分别使用 `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`。
- **效果**: 由于没有了 `@Data`，Lombok 不会尝试生成任何构造函数，完全由开发者自己控制。只要我们不主动创建无参构造函数，就不会有编译问题。
- **优点**: 提供了最强的控制力，代码意图最清晰。
- **缺点**: 注解数量增多，略显繁琐。

### 场景四: 模拟审计实体

- **位置**: `solution.audit`
- **做法**: 这是对真实世界中 JPA `BaseEntity` 场景的模拟。基类包含一个 `final` 的 `createdAt` 字段，并采用**方案二**（显式提供默认构造函数）来初始化这个时间戳。
- **效果**: 完美契合了业务场景，既满足了 Lombok 的要求，也保证了审计字段的有效性。

## 3. 如何验证

本项目包含了针对每一种解决方案的单元测试，位于 `src/test/java` 目录下。

你可以运行 `./gradlew build` 来执行完整的编译和测试。

- `ForceSolutionTest`: 验证了 `force=true` 会导致 `createdBy` 为 `null`。
- `ExplicitSolutionTest`: 验证了 `createdBy` 被正确设置为默认值 `"system"`。
- `TargetedSolutionTest`: 验证了精确注解下对象的行为。
- `AuditSolutionTest`: 验证了审计场景下 `createdAt` 字段被成功初始化。
# JSpecify 示例项目

本项目旨在演示如何在 Java 项目中结合 JSpecify 规范与 Checker Framework 进行全面的空安全分析，从而在编译时捕获潜在的 `NullPointerException`。

## 核心技术简介

### JSpecify

JSpecify 是一个由 Google 支持的开源项目，旨在标准化 Java 中的空值注解。它提供了一套统一的注解（`@NonNull`, `@Nullable`, `@NullMarked`, `@NullUnmarked`）和明确的规范，使得不同的工具（IDE、静态分析器等）能够协同工作，提升 Java 生态系统的空安全水平。其核心原则包括：

*   **标准化**：提供统一的空值注解和规范。
*   **默认非空**：通过 `@NullMarked` 注解，可以在包、类或模块级别设置默认非空行为，减少注解的冗余。
*   **渐进式采用**：支持逐步引入空安全检查。

### Checker Framework

Checker Framework 是一个强大的工具，通过“可插拔类型检查”扩展了 Java 的类型系统。它允许开发者在编译时对程序进行更严格的检查，从而在早期发现并预防错误。本项目主要使用了其 **Nullness Checker**，它利用 JSpecify 注解来防止空指针异常。

## 项目概述

本项目包含以下核心组件，每个组件都旨在演示 JSpecify 和 Checker Framework 的特定功能：

*   **`package-info.java` (com.example)**：使用 `@NullMarked` 注解，将 `com.example` 包下的所有未注解类型默认视为 `@NonNull`。
*   **`Person.java`**：演示了 `@NonNull`（默认）和 `@Nullable` 注解在类字段、构造函数参数和方法返回类型上的应用。
*   **`Box.java`**：展示了 JSpecify 如何处理泛型类型以及包含 `@Nullable` 元素的集合。
*   **`LegacyCode.java` (com.example.unspecified)**：位于一个没有 `@NullMarked` 注解的包中，模拟了“空值未指定”（nullness-unspecified）的旧代码。
*   **`UnmarkedExample.java` (com.example.unmarked)**：位于一个使用 `@NullUnmarked` 注解的包中，演示了如何显式地取消 `@NullMarked` 的默认行为，使该包下的类型默认为“空值未指定”。
*   **`Main.java`**：项目的入口点，通过调用上述类的示例方法，全面演示了 JSpecify 和 Checker Framework 的各种特性。

## 编译期错误演示

本项目特意保留了一个编译错误，以演示 JSpecify 和 Checker Framework 在编译时捕获潜在 `NullPointerException` 的能力：

*   **`LegacyCode.java` 中的错误**：在 `LegacyCode.java` 的 `createPersonWithUnspecifiedName` 方法中，尝试将一个可能为 `null` 的值（`unspecifiedName`，其类型为 `@Nullable String`）传递给 `Person` 构造函数中需要 `@NonNull String` 的参数。Checker Framework 会在此处报告一个编译错误，因为它无法保证 `unspecifiedName` 永远非空。这个错误被保留，旨在强调 JSpecify 如何在编译时强制执行空安全契约，即使是在与旧代码交互时。

## 如何构建

要构建项目并查看空安全分析的实际效果，请运行以下命令：

```bash
./gradlew build
```

**注意**：构建将会失败，这是预期行为，因为项目中特意保留了一个编译错误（如上所述）来演示 JSpecify 的空安全检查功能。

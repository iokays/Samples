# Spring Modulith 示例项目

本项目旨在演示如何在 Spring Boot 应用中使用 Spring Modulith 来构建结构清晰的模块化单体应用。

## 核心概念

Spring Modulith 帮助开发者在单个 Spring Boot 项目中强制实施逻辑模块边界。主要特性包括：

*   **应用模块 (Application Modules)**: 通过包结构定义清晰的模块。
*   **模块间交互**: 提倡使用发布的事件 (Application Events) 进行模块间的异步通信。
*   **模块依赖验证**: 自动验证模块间的依赖关系是否符合预设规则。
*   **模块集成测试**: 提供 `@ApplicationModuleTest` 等工具，方便对单个模块进行隔离测试。

## 项目结构

本项目包含两个逻辑模块：

*   `order`: 负责处理订单逻辑。当一个订单完成时，它会发布一个 `OrderCompleted` 事件。
*   `inventory`: 负责处理库存逻辑。它会监听 `OrderCompleted` 事件，并相应地更新库存（在本示例中仅为打印日志）。

## 如何运行

理论上，可以使用以下命令构建和运行项目：

```bash
./gradlew build
./gradlew bootRun
```

## 【重要】构建失败记录

在开发过程中，本项目遇到了一个无法解决的构建失败问题。

*   **问题**: 执行 `./gradlew build` 时，构建在 `:resolveMainClassName` 任务处失败。
*   **错误**: `Unsupported class file major version 69`
*   **环境**:
    *   Java: 25
    *   Spring Boot: 3.4.0-M3
    *   Spring Modulith: 2.0.0-RC1
    *   Gradle: 9.1.0

**分析**:

错误 `Unsupported class file major version 69` 表明，执行 Gradle 任务的 JVM 版本低于项目编译所用的 JDK 25。尽管已尝试多种方法强制 Gradle 使用 JDK 25，但 Spring Boot 的 `resolveMainClassName` 任务似乎仍在不兼容的 JVM 上运行，导致其无法解析为 JDK 25 编译的类文件。这很可能是 Spring Boot Gradle 插件与前沿的 JDK 25 之间的一个兼容性问题。

**已尝试的修复策略（均失败）**:

1.  **升级 Lombok**: 尝试了多个 Lombok 版本（包括快照版），以解决潜在的注解处理器与 JDK 25 的兼容问题。
2.  **移除 Lombok**: 完全移除了 Lombok 依赖，并手动编写了所有样板代码（构造函数、Getter等）。
3.  **配置 Gradle 工具链**: 在 `build.gradle.kts` 中明确设置 `java.toolchain` 为 Java 25。
5.  **停止 Gradle 守护进程**: 使用 `./gradlew --stop` 强制停止所有守护进程，以确保新配置生效。
6.  **不使用 BOM 方式管理 Spring Modulith 依赖**: 移除了 `dependencyManagement` 块中对 Spring Modulith BOM 的导入，并为相关依赖明确指定了版本。

尽管构建失败，但项目中的代码仍然清晰地演示了如何使用 Spring Modulith 组织模块和进行事件驱动的通信。
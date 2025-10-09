# 使用JFR与动态代理，构建强大的废弃API“侦探”

本项目是一个综合示例，展示了如何结合使用Java Flight Recorder (JFR) 和动态代理，为Java项目建立一套主动、全面的废弃API调用检测机制。项目被清晰地划分为两个步骤，模拟了真实世界中的“数据生成”与“分析报告”流程。

## 核心策略

1.  **被动检测 (JFR)**: 利用JDK 22+ 内置的`jdk.DeprecatedInvocation`事件，在运行时捕获对JDK内部废弃API的调用。这是一种低开销、非侵入式的监控方式。
2.  **主动防御 (动态代理)**: 通过Java的动态代理机制，在开发和测试阶段就主动拦截对项目自身代码中废弃方法的调用，并发出警告。这是一种前置的、预防性的策略。

## 如何运行与验证

### 步骤 1: 生成JFR数据

首先，我们运行`ApiCaller`程序。它的唯一职责是调用一些JDK的废弃方法。我们通过特定的JVM参数来启动它，以开启JFR记录功能。

1.  **编译项目**

    ```bash
    ./gradlew build
    ```

2.  **运行ApiCaller并生成记录文件**

    此命令会执行`ApiCaller`，并将所有`jdk.DeprecatedInvocation`事件捕获到`deprecated-calls.jfr`文件中。

    ```bash
    java -XX:StartFlightRecording:jdk.DeprecatedInvocation#level=all,filename=deprecated-calls.jfr -cp build/classes/java/main com.iokays.sample.ApiCaller
    ```

    运行结束后，你会在项目根目录下看到`deprecated-calls.jfr`文件。

### 步骤 2: 分析并生成报告

接下来，我们运行`ReportGenerator`程序。它不需要任何特殊的JVM参数。

```bash
java -cp build/classes/java/main com.iokays.sample.ReportGenerator
```

这个程序会执行两个任务，并在控制台打印出结果：

*   **JFR分析**: 它会读取上一步生成的`deprecated-calls.jfr`文件，并列出所有通过JFR发现的JDK废弃方法调用。
*   **动态代理演示**: 它会演示动态代理机制，在调用我们自己代码库中的一个废弃方法时，如何实时打印出警告信息。

这个清晰的两步流程展示了如何有效地分离数据收集与数据分析，从而构建一个健壮的技术负债监控方案。

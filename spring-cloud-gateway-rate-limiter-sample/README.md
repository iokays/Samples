# Spring Cloud Gateway 限流示例

本项目演示了如何使用 Spring Cloud Gateway 和 Redis 实现基于 IP 地址的 API 限流。

## 核心技术

*   **Spring Cloud Gateway**: 提供 API 网关功能。
*   **Spring Boot**: 用于快速构建应用程序。
*   **Redis**: 作为令牌桶算法的存储，用于记录和管理请求速率。
*   **Project Reactor**: Spring Cloud Gateway 基于响应式编程模型构建。

## 多种限流策略

本项目演示了四种不同的限流策略，每种策略都通过一个独立的 `KeyResolver` 和路由规则进行配置。

1.  **按IP地址限流 (ip_limited_route)**
2.  **按用户限流 (user_limited_route)**
3.  **按请求路径限流 (path_limited_route)**
4.  **按请求头限流 (header_limited_route)**

## 如何运行

### 前提条件

*   JDK 25 或更高版本。
*   一个正在运行的 Docker 环境。

### 步骤

1.  **启动Redis容器**:

    ```bash
    docker run -d --name redis-for-gateway -p 6379:6379 redis
    ```

2.  **构建项目**:

    ```bash
    ./gradlew build
    ```

3.  **运行应用程序**:

    ```bash
    java -jar build/libs/spring-cloud-gateway-rate-limiter-sample-0.0.1-SNAPSHOT.jar
    ```

    应用程序将在 `8080` 端口启动。

## 如何测试

你可以使用 `curl` 或任何其他 HTTP 客户端来测试不同的限流功能。

### 1. 测试按IP地址限流

此路由 (`/ip-limited/get`) 的限流规则是 **每秒1个请求**。

*   **第一个请求 (成功)**:
    ```bash
    curl http://localhost:8080/ip-limited/get
    ```
*   **第二个请求 (1秒内，失败)**:
    ```bash
    curl -v http://localhost:8080/ip-limited/get
    ```
    你将看到 `429 Too Many Requests`。

### 2. 测试按用户限流

此路由 (`/user-limited/get`) 需要HTTP Basic认证，限流规则是 **每秒1个请求**。我们预设了一个用户 `user`，密码 `password`。

*   **第一个请求 (成功)**:
    ```bash
    curl -u user:password http://localhost:8080/user-limited/get
    ```
*   **第二个请求 (1秒内，失败)**:
    ```bash
    curl -v -u user:password http://localhost:8080/user-limited/get
    ```
    你将看到 `429 Too Many Requests`。
*   **切换用户 (成功)**: 如果你用另一个（不存在的）用户认证，会被拒绝访问，但不会触发限流。

### 3. 测试按请求路径限流

此路由 (`/path-limited/**`) 的限流规则是 **每秒2个请求**，并且是根据完整的请求路径作为键。

*   **测试路径1 (成功)**:
    ```bash
    curl http://localhost:8080/path-limited/a
    curl http://localhost:8080/path-limited/a
    ```
*   **测试路径1 (1秒内第3次，失败)**:
    ```bash
    curl -v http://localhost:8080/path-limited/a
    ```
    你将看到 `429 Too Many Requests`。
*   **测试路径2 (成功)**: 换一个路径，你有新的令牌桶。
    ```bash
    curl http://localhost:8080/path-limited/b
    curl http://localhost:8080/path-limited/b
    ```

### 4. 测试按请求头限流

此路由 (`/header-limited/get`) 的限流规则是 **每秒1个请求**，根据 `X-Client-Id` 请求头的值作为键。

*   **客户端1 - 第一个请求 (成功)**:
    ```bash
    curl -H "X-Client-Id: client-1" http://localhost:8080/header-limited/get
    ```
*   **客户端1 - 第二个请求 (1秒内，失败)**:
    ```bash
    curl -v -H "X-Client-Id: client-1" http://localhost:8080/header-limited/get
    ```
    你将看到 `429 Too Many Requests`。
*   **客户端2 - 第一个请求 (成功)**: 换一个 `X-Client-Id`，你有新的令牌桶。
    ```bash
    curl -H "X-Client-Id: client-2" http://localhost:8080/header-limited/get
    ```
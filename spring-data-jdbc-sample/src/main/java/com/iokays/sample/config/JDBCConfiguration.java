package com.iokays.sample.config;

import org.springframework.context.annotation.Configuration;

/**
 * 因为单数据源, Spring Boot 会自动配置 JDBC 的相关配置, 所以这里不需要做任何配置
 */
@Configuration
public class JDBCConfiguration {
}


// Path: D:/Samples/spring-cloud-gateway-rate-limiter-sample/src/main/java/com/example/config/RateLimiterConfig.java
package com.example.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 限流配置类
 */
@Configuration
public class RateLimiterConfig {

    /**
     * 根据IP地址进行限流的KeyResolver
     *
     * @return KeyResolver
     */
    @Bean
    @Primary
    public KeyResolver remoteAddrKeyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress());
    }

    /**
     * 根据用户进行限流的KeyResolver
     *
     * @return KeyResolver
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> exchange.getPrincipal().map(principal -> principal.getName());
    }

    /**
     * 根据请求路径进行限流的KeyResolver
     *
     * @return KeyResolver
     */
    @Bean
    public KeyResolver pathKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

    /**
     * 根据请求头进行限流的KeyResolver
     *
     * @return KeyResolver
     */
    @Bean
    public KeyResolver headerKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getHeaders().getFirst("X-Client-Id"));
    }

}

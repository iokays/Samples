// Path: D:/Samples/spring-cloud-gateway-rate-limiter-sample/src/main/java/com/example/config/SecurityConfig.java
package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Spring Security 配置
 */
@Configuration
public class SecurityConfig {

    /**
     * 配置一个内存中的用户，用于测试
     *
     * @return MapReactiveUserDetailsService
     */
    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
            .password("{noop}password")
            .roles("USER")
            .build();
        return new MapReactiveUserDetailsService(user);
    }

    /**
     * 配置安全过滤链，启用HTTP Basic认证
     *
     * @param http ServerHttpSecurity
     * @return SecurityWebFilterChain
     */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchange -> exchange.anyExchange().authenticated())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}

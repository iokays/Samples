package com.iokays.sample.config;

import io.vavr.Tuple3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity //默认启用所有常用功能（包括 @PreAuthorize）
public class SecurityConfiguration {

    /**
     * 还原默认配置
     * @see org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration
     */
    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.httpBasic(withDefaults());
        http.formLogin(withDefaults());
        http.logout(withDefaults());
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        final Tuple3<String, String, String> user = new Tuple3<>("user", "pw-123456", "USER");

        log.info("user: {}", user);

        return (username) -> {
            if ("user".equals(username)) {
                return User.withUsername(user._1()).password(passwordEncoder.encode(user._2())).roles(user._3()).build();
            }
            throw new UsernameNotFoundException("User not found: " + username);
        };
    }
}

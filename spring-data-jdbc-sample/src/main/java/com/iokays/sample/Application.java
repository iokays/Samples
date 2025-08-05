package com.iokays.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories // 启用 JDBC Repository, 必须的, 并且会扫描当前包(递归子包)下的 Repository
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

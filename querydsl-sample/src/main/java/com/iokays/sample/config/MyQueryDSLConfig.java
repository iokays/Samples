package com.iokays.sample.config;

import com.querydsl.sql.PostgreSQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.spring.SpringConnectionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyQueryDSLConfig {

    @Bean
    public SQLQueryFactory sqlQueryFactory(DataSource dataSource) {
        final var configuration = new com.querydsl.sql.Configuration(new PostgreSQLTemplates());
        return new SQLQueryFactory(configuration, new SpringConnectionProvider(dataSource));
    }


}

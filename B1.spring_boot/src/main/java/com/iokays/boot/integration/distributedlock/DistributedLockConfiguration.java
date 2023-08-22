package com.iokays.boot.integration.distributedlock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.jdbc.lock.DefaultLockRepository;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.integration.jdbc.lock.LockRepository;

import javax.sql.DataSource;

/**
 * 分布式锁的配置
 */
@Configuration
public class DistributedLockConfiguration {

    @Bean
    public DefaultLockRepository defaultLockRepository(DataSource dataSource){
        return new DefaultLockRepository(dataSource);
    }

    @Bean
    public JdbcLockRegistry jdbcLockRegistry(LockRepository lockRepository){
        return new JdbcLockRegistry(lockRepository);
    }

}

package com.iokays.boot.redis.inegration.distributedlock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;



/**
 * 分布式锁的配置
 */
@Configuration
public class DistributedLockConfiguration {

    @Bean
    public RedisLockRegistry defaultLockRepository(RedisConnectionFactory connectionFactory){
        return new RedisLockRegistry(connectionFactory, "redis-lock");
    }

}

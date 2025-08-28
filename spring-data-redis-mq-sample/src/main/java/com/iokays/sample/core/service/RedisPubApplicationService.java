package com.iokays.sample.core.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RedisPubApplicationService {

    private final RedisOperations<String, Object> redisOperations;

    public void publish(Object message) {
        redisOperations.convertAndSend("chatroom", message);
    }

}

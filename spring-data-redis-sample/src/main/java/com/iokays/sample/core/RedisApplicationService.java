package com.iokays.sample.core;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RedisApplicationService {

    private final RedisTemplate<String, Object> redisTemplate;


    public void delete(String key) {
        redisTemplate.delete(key);
    }

    // String 格式
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // Hash 格式
    public void setHash(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    // Hash 格式 删除
    public Object getHash(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    // List 格式
    public void addList(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    // List 格式 取值
    public List<Object> getList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    // Set 格式
    public void addSet(String key, Object value) {
        redisTemplate.opsForSet().add(key, value);
    }

    // Set 格式 全部取值
    public Set<Object> getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    // ZSet 格式
    public void addZSet(String key, Object value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    // ZSet 格式 取值
    public Set<Object> getZSet(String key) {
        return redisTemplate.opsForZSet().range(key, 0, -1);
    }

    //位图 格式
    public void setBit(String key, long offset, boolean value) {
        redisTemplate.opsForValue().setBit(key, offset, value);
    }

    //位图 格式 取值
    public boolean getBit(String key, long offset) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().getBit(key, offset));
    }

}

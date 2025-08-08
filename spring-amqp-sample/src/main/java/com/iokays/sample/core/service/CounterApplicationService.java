package com.iokays.sample.core.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@AllArgsConstructor
public class CounterApplicationService {

    private final Map<String, Pair<LocalDateTime, Long>> counter = new ConcurrentHashMap<>();

    synchronized // 为什么需要加锁? 因为increment方法不是线程安全的, 两个线程同时调用increment方法, 会导致计数错误. 跨模块可以使用分布式锁
    public void increment(String customerId, LocalDateTime time) {
        final Pair<LocalDateTime, Long> old = counter.computeIfAbsent(customerId, k -> Pair.of(time, 0L));
        counter.put(customerId, Pair.of(time, old.getValue() + 1));
    }

    public Pair<LocalDateTime, Long> get(String customerId) {
        return counter.get(customerId);
    }

}

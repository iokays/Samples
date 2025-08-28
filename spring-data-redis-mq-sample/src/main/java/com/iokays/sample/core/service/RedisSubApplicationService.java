package com.iokays.sample.core.service;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RedisSubApplicationService {

    private final List<String> historyMessages = Lists.newCopyOnWriteArrayList();

    static void main() {
        List<String> list = Lists.newCopyOnWriteArrayList();
        list.add("hello");
        log.info("historyMessages:{}, class: {}", list, list.getFirst().getClass());


    }

    public void handleMessage(String message) {
        historyMessages.add(message);
        log.info("message:{}", message);
    }

    public boolean contains(String message) {
        return historyMessages.contains(message);
    }

}

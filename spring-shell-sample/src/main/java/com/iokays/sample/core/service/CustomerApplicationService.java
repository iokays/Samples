package com.iokays.sample.core.service;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerApplicationService {

    private final Map<Integer, String> customers = new ConcurrentHashMap<>();
    ;

    public Map<Integer, String> findAll() {
        return ImmutableMap.copyOf(customers);
    }

    //用例,不考虑并发问题
    public Integer insert(String name) {
        final var id = customers.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        customers.put(id, name);
        return id;
    }

    //用例,不考虑并发问题
    public void update(Integer id, String name) {
        Validate.isTrue(customers.containsKey(id), "该Id: %s不存在".formatted(id));
        customers.put(id, name);
    }

    public String findById(Integer id) {
        return customers.getOrDefault(id, StringUtils.EMPTY);
    }


    public void delete(Integer id) {
        customers.remove(id);
    }


}

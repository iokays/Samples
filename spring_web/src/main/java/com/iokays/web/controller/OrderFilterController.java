package com.iokays.web.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletInputStream;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class OrderFilterController {

    @Resource
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/filters")
    public List<Pair<String, Integer>> filters() {
        final var orderedFilters = applicationContext.getBeansOfType(OrderedFilter.class);
        return orderedFilters.keySet().stream()
                .map(key -> Pair.of(key, orderedFilters.get(key).getOrder()))
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
    }
}

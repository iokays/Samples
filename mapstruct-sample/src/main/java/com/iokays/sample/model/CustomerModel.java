package com.iokays.sample.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 这是一个出栈或入栈的模型对象
 *
 * @param id
 * @param customerName
 * @param source       来源
 * @param registerAt
 */
public record CustomerModel(
        String id,
        String customerName,
        String amount,
        String source,
        LocalDate registerAt,
        LocalDateTime now,
        Set<String> actions
) {
}

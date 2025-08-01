package com.iokays.sample.domain;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 这是一个实体
 */
@Getter
@Builder
public class Customer implements Serializable {
    private Long id;
    private String name;
    private BigDecimal amount;
    private LocalDateTime registerAt;
}

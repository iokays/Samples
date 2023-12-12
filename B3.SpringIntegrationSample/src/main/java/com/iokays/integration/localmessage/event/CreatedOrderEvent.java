package com.iokays.integration.localmessage.event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreatedOrderEvent extends DomainEvent implements Serializable {

    private final String orderId;

    private final BigDecimal amount;

    private final LocalDateTime createTime;

    public CreatedOrderEvent(String orderId, BigDecimal amount, LocalDateTime createTime) {
        super();
        this.orderId = orderId;
        this.amount = amount;
        this.createTime = createTime;
    }
}

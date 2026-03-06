// Path: Samples/spring-modulith-sample/src/main/java/com/iokays/sample/order/Order.java
package com.iokays.sample.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "ORDERS")
public class Order {

    private final @Id UUID id;
    private boolean completed;

    public Order() {
        this.id = UUID.randomUUID();
        this.completed = false;
    }

    /**
     * 将订单标记为完成。
     */
    public Order complete() {
        this.completed = true;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public boolean isCompleted() {
        return completed;
    }
}

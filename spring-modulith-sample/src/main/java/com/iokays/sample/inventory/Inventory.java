// Path: Samples/spring-modulith-sample/src/main/java/com/iokays/sample/inventory/Inventory.java
package com.iokays.sample.inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Inventory {

    private final @Id UUID id;
    private long stock;

    protected Inventory() {
        this.id = null;
    }

    public Inventory(String name, long stock) {
        this.id = UUID.randomUUID();
        this.stock = stock;
    }

    /**
     * 减少库存。
     *
     * @param amount 减少的数量
     */
    public void decreaseStock(long amount) {
        this.stock -= amount;
    }

    public UUID getId() {
        return id;
    }

    public long getStock() {
        return stock;
    }
}

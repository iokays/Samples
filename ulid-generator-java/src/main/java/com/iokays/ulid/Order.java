package com.iokays.ulid;

import com.github.f4b6a3.ulid.Ulid;

import java.time.Instant;

/**
 * 订单类，使用ULID作为主键
 */
public class Order implements Comparable<Order> {

    private final Ulid id;
    private final String product;
    private final Instant orderTime;

    public Order(String product) {
        // 在创建订单时，直接生成一个ULID作为ID
        this.id = UlidCreator.getUlid();
        this.product = product;
        this.orderTime = Instant.ofEpochMilli(this.id.getTime());
    }

    public Ulid getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public Instant getOrderTime() {
        return orderTime;
    }

    @Override
    public String toString() {
        return String.format("Order{id=%s, product='%s', orderTime=%s}", id, product, orderTime);
    }

    /**
     * 实现Comparable接口，以便可以直接对订单列表进行排序
     * 排序规则就是简单地比较ULID的字符串形式
     */
    @Override
    public int compareTo(Order other) {
        return this.id.toString().compareTo(other.id.toString());
    }
}

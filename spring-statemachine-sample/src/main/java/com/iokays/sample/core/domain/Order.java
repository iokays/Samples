package com.iokays.sample.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor // Lombok 无参构造函数
public class Order {

    private String id;
    private String orderCode; // 订单编号
    private OrderStates status;    // 订单状态
    private String customerId; // 客户ID
    private double amount;     // 订单金额
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private Long version; // 乐观锁

    public Order(String customerId, double amount) {
        this.id = UUID.randomUUID().toString();
        this.orderCode = "ORD-" + System.currentTimeMillis();
        this.status = OrderStates.DRAFT; // 初始状态
        this.customerId = customerId;
        this.amount = amount;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 仅用于状态机内部更新状态
     *
     * @param newState 新状态
     */
    public void transitionTo(OrderStates newState) {
        this.status = newState;
        this.updateTime = LocalDateTime.now();
        // 这里可以发布领域事件，例如：registerEvent(new OrderStateChangedEvent(this.id, this.status));
    }

    // 可以在这里添加一些业务方法，例如：
    public void pay() {
        // 支付逻辑
        System.out.println("Order " + orderCode + " paid.");
    }

    public void ship() {
        // 发货逻辑
        System.out.println("Order " + orderCode + " shipped.");
    }

    public void deliver() {
        // 签收逻辑
        System.out.println("Order " + orderCode + " delivered.");
    }

    public void cancel() {
        // 取消逻辑
        System.out.println("Order " + orderCode + " cancelled.");
    }

    public OrderStates getStatus() {
        return status;
    }

    public void complete() {
        // 完成逻辑
        System.out.println("Order " + orderCode + " completed.");
    }
}
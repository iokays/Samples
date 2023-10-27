package com.iokays.axon.order.event;

/**
 * 订单已发货事件
 */
public class OrderShippedEvent {

    private final String orderId;

    public OrderShippedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

}
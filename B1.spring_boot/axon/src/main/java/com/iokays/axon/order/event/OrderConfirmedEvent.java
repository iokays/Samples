package com.iokays.axon.order.event;

/**
 * 订单已确认事件
 */
public class OrderConfirmedEvent {
 
    private final String orderId;

    public OrderConfirmedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
 
}
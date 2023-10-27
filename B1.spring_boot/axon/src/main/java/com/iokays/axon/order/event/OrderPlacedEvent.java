package com.iokays.axon.order.event;

/**
 * 订单已下单事件
 */
public class OrderPlacedEvent {
 
    private final String orderId;
    private final String product;

    public OrderPlacedEvent(String orderId, String product) {
        this.orderId = orderId;
        this.product = product;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProduct() {
        return product;
    }
 
}
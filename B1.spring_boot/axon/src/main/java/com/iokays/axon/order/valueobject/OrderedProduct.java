package com.iokays.axon.order.valueobject;

public class OrderedProduct {

    private final String orderId;
    private final String product;
    private OrderStatus orderStatus;

    public OrderedProduct(String orderId, String product) {
        this.orderId = orderId;
        this.product = product;
        this.orderStatus = OrderStatus.PLACED;
    }

    public void setOrderConfirmed() {
        this.orderStatus = OrderStatus.CONFIRMED;
    }

    public void setOrderShipped() {
        this.orderStatus = OrderStatus.SHIPPED;
    }

    @Override
    public String toString() {
        //guava 重写
        return com.google.common.base.MoreObjects.toStringHelper(this)
                .add("orderId", orderId)
                .add("product", product)
                .add("orderStatus", orderStatus)
                .toString();
    }
}
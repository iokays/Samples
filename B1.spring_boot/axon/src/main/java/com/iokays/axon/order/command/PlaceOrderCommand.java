package com.iokays.axon.order.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 下单命令
 */
public class PlaceOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
    private final String product;

    public PlaceOrderCommand(String orderId, String product) {
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
 

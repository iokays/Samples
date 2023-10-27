package com.iokays.axon.order.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ShipOrderCommand {
 
    @TargetAggregateIdentifier
    private final String orderId;

    public ShipOrderCommand(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
 
}
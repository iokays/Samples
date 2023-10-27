package com.iokays.axon.order.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 确认订单命令
 */
public class ConfirmOrderCommand {
 
    @TargetAggregateIdentifier
    private final String orderId;

    public ConfirmOrderCommand(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
   
}
package com.iokays.axon.order.domain;

import com.iokays.axon.order.command.ConfirmOrderCommand;
import com.iokays.axon.order.command.PlaceOrderCommand;
import com.iokays.axon.order.command.ShipOrderCommand;
import com.iokays.axon.order.event.OrderConfirmedEvent;
import com.iokays.axon.order.event.OrderPlacedEvent;
import com.iokays.axon.order.event.OrderShippedEvent;
import com.iokays.axon.order.exception.UnconfirmedOrderException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;

    @CommandHandler
    public OrderAggregate(PlaceOrderCommand command) {
        apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        apply(new OrderConfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) {
        if (!orderConfirmed) {
            throw new UnconfirmedOrderException();
        }
        apply(new OrderShippedEvent(orderId));
    }

    @EventSourcingHandler
    public void on(OrderPlacedEvent event) {
        this.orderId = event.getOrderId();
        orderConfirmed = false;
        System.out.println("OrderPlacedEvent");
    }

    protected OrderAggregate() { }
}
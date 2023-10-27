package com.iokays.axon.order.domain;

import com.iokays.axon.order.command.PlaceOrderCommand;
import com.iokays.axon.order.command.ShipOrderCommand;
import com.iokays.axon.order.event.OrderConfirmedEvent;
import com.iokays.axon.order.event.OrderPlacedEvent;
import com.iokays.axon.order.exception.UnconfirmedOrderException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class OrderAggregateTester {

    private FixtureConfiguration<OrderAggregate> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }

    @Test
    public void testPlaceOrder() {
        final var orderId = UUID.randomUUID().toString();
        final var product = "product";

        fixture.givenNoPriorActivity()
                .when(new PlaceOrderCommand(orderId, product))
                .expectEvents(new OrderPlacedEvent(orderId, product));
    }

    @Test
    public void testPlaceOrder2() {
        String orderId = UUID.randomUUID().toString();
        String product ="Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product), new OrderConfirmedEvent(orderId))
                .when(new ShipOrderCommand(orderId))
                .expectException(UnconfirmedOrderException.class);
    }


}

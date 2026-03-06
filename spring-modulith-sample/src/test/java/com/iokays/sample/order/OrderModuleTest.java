// Path: Samples/spring-modulith-sample/src/test/java/com/iokays/sample/order/OrderModuleTest.java
package com.iokays.sample.order;

import com.iokays.sample.App;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest(App.class)
class OrderModuleTest {

    @Autowired
    private OrderManagement orders;

    @Test
    void createsOrderAndPublishesEvent(Scenario scenario) {
        var order = orders.create();

        scenario.stimulate(() -> orders.complete(order))
                .andWaitForEventOfType(OrderCompleted.class)
                .toArriveAndVerify(event -> {
                    assertThat(event.orderId()).isEqualTo(order.getId());
                });
    }
}

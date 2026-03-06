// Path: Samples/spring-modulith-sample/src/test/java/com/iokays/sample/ApplicationIntegrationTest.java
package com.iokays.sample;

import com.iokays.sample.order.OrderCompleted;
import com.iokays.sample.order.OrderManagement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.test.Scenario;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = App.class)
class ApplicationIntegrationTest {

    @Autowired
    OrderManagement orders;

    @Test
    void completesOrderAndUpdatesInventory(Scenario scenario) {
        var order = orders.create();

        scenario.stimulate(() -> orders.complete(order))
                .andWaitForEventOfType(OrderCompleted.class)
                .toArriveAndVerify(event -> {
                    assertThat(event.orderId()).isEqualTo(order.getId());
                });
    }
}

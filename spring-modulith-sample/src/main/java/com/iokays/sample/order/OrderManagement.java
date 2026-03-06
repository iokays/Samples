// Path: Samples/spring-modulith-sample/src/main/java/com/iokays/sample/order/OrderManagement.java
package com.iokays.sample.order;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderManagement {

    private final OrderRepository orders;
    private final ApplicationEventPublisher events;

    public OrderManagement(OrderRepository orders, ApplicationEventPublisher events) {
        this.orders = orders;
        this.events = events;
    }

    /**
     * 创建一个新订单并将其保存到仓库中。
     *
     * @return 创建的订单
     */
    @Transactional
    public Order create() {
        var order = new Order();
        return orders.save(order);
    }

    /**
     * 将订单标记为完成，保存并发布一个 {@link OrderCompleted} 事件。
     *
     * @param order 要完成的订单
     */
    @Transactional
    public void complete(Order order) {
        // 预计输出:
        // 订单 [orderId] 已完成。
        System.out.println("订单 " + order.getId() + " 已完成。");
        var completedOrder = orders.save(order.complete());
        events.publishEvent(new OrderCompleted(completedOrder.getId()));
    }
}

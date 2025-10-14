package com.iokays.sample.core.service;

import com.iokays.sample.core.domain.OrderStateMachineEvents;
import com.iokays.sample.core.domain.OrderStates;
import com.iokays.sample.core.domain.Order;
import com.iokays.sample.core.domain.OrderRepository;
import com.iokays.sample.core.domain.OrderStateMachineExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderApplicationService {

    private final OrderStateMachineExecutor<OrderStates, OrderStateMachineEvents> stateMachineExecutor; // 注入泛型化的执行器
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(CreateOrderCommand command) {
        Order newOrder = new Order(command.customerId(), command.amount());

        // 创建订单时，触发 CREATE_ORDER 事件
        boolean success = stateMachineExecutor.execute(
                newOrder,
                OrderStateMachineEvents.CREATE_ORDER,
                null // 创建订单可能不需要额外参数传递给 Action
        );

        if (!success) {
            throw new IllegalStateException("无法创建订单！");
        }
        orderRepository.save(newOrder); // 持久化聚合根
        System.out.println("Order created successfully: " + newOrder.getOrderCode() + " Current status: " + newOrder.getStatus());
        return newOrder;
    }

    @Transactional
    public void payOrder(PayOrderCommand command) {
        Order order = orderRepository.findByOrderCode(command.orderCode())
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + command.orderCode()));

        boolean success = stateMachineExecutor.execute(
                order,
                OrderStateMachineEvents.PAY_ORDER,
                Map.of("paymentMethod", command.paymentMethod()) // 将支付方式作为参数传递给 Action
        );

        if (!success) {
            throw new IllegalStateException("订单当前状态无法支付！");
        }
        orderRepository.save(order);
        System.out.println("Order " + order.getOrderCode() + " paid. Current status: " + order.getStatus());
    }

    @Transactional
    public void shipOrder(ShipOrderCommand command) {
        Order order = orderRepository.findByOrderCode(command.orderCode())
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + command.orderCode()));

        boolean success = stateMachineExecutor.execute(
                order,
                OrderStateMachineEvents.SHIP_ORDER,
                Map.of("trackingNumber", command.trackingNumber()) // 传递物流单号
        );

        if (!success) {
            throw new IllegalStateException("订单当前状态无法发货！");
        }
        orderRepository.save(order);
        System.out.println("Order " + order.getOrderCode() + " shipped. Current status: " + order.getStatus());
    }

    @Transactional
    public void deliverOrder(DeliverOrderCommand command) {
        Order order = orderRepository.findByOrderCode(command.orderCode())
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + command.orderCode()));

        boolean success = stateMachineExecutor.execute(order, OrderStateMachineEvents.DELIVER_ORDER, null);

        if (!success) {
            throw new IllegalStateException("订单当前状态无法签收！");
        }
        orderRepository.save(order);
        System.out.println("Order " + order.getOrderCode() + " delivered. Current status: " + order.getStatus());
    }

    @Transactional
    public void completeOrder(CompleteOrderCommand command) {
        Order order = orderRepository.findByOrderCode(command.orderCode())
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + command.orderCode()));

        boolean success = stateMachineExecutor.execute(order, OrderStateMachineEvents.COMPLETE_ORDER, null);

        if (!success) {
            throw new IllegalStateException("订单当前状态无法完成！");
        }
        orderRepository.save(order);
        System.out.println("Order " + order.getOrderCode() + " completed. Current status: " + order.getStatus());
    }

    @Transactional
    public void cancelOrder(CancelOrderCommand command) {
        Order order = orderRepository.findByOrderCode(command.orderCode())
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + command.orderCode()));

        boolean success = stateMachineExecutor.execute(
                order,
                OrderStateMachineEvents.CANCEL_ORDER,
                Map.of("reason", command.reason()) // 传递取消原因
        );

        if (!success) {
            throw new IllegalStateException("订单当前状态无法取消！");
        }
        orderRepository.save(order);
        System.out.println("Order " + order.getOrderCode() + " cancelled. Current status: " + order.getStatus());
    }

    public Optional<Order> getOrderByCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode);
    }

    // --- Command DTOs (示例，你可以定义更详细的 DTO) ---
    // 实际项目中这些会是独立的类
    public record CreateOrderCommand(String customerId, double amount) {
    }

    public record PayOrderCommand(String orderCode, String paymentMethod) {
    }

    public record ShipOrderCommand(String orderCode, String trackingNumber) {
    }

    public record DeliverOrderCommand(String orderCode) {
    }

    public record CancelOrderCommand(String orderCode, String reason) {
    }

    public record CompleteOrderCommand(String orderCode) {
    }
}
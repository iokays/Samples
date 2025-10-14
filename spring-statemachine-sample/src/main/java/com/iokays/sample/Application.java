package com.iokays.sample;

import com.iokays.sample.core.domain.Order;
import com.iokays.sample.core.service.OrderApplicationService;
import com.iokays.sample.core.service.OrderApplicationService.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run(OrderApplicationService orderApplicationService) {
        return args -> {
            System.out.println("--- 订单状态机示例启动 ---");

            // 1. 创建订单
            CreateOrderCommand createCommand = new CreateOrderCommand("customer123", 100.00);
            Order order = orderApplicationService.createOrder(createCommand);
            System.out.println("订单当前状态: " + order.getStatus());

            // 2. 支付订单
            PayOrderCommand payCommand = new PayOrderCommand(order.getOrderCode(), "Alipay");
            orderApplicationService.payOrder(payCommand);
            Order paidOrder = orderApplicationService.getOrderByCode(order.getOrderCode()).get();
            System.out.println("订单当前状态: " + paidOrder.getStatus());

            // 3. 发货
            ShipOrderCommand shipCommand = new ShipOrderCommand(order.getOrderCode(), "SF123456789");
            orderApplicationService.shipOrder(shipCommand);
            Order shippedOrder = orderApplicationService.getOrderByCode(order.getOrderCode()).get();
            System.out.println("订单当前状态: " + shippedOrder.getStatus());

            // 4. 签收
            DeliverOrderCommand deliverCommand = new DeliverOrderCommand(order.getOrderCode());
            orderApplicationService.deliverOrder(deliverCommand);
            Order deliveredOrder = orderApplicationService.getOrderByCode(order.getOrderCode()).get();
            System.out.println("订单当前状态: " + deliveredOrder.getStatus());

            // 5. 完成订单
            CompleteOrderCommand completeCommand = new CompleteOrderCommand(order.getOrderCode());
            orderApplicationService.completeOrder(completeCommand);
            Order completedOrder = orderApplicationService.getOrderByCode(order.getOrderCode()).get();
            System.out.println("订单当前状态: " + completedOrder.getStatus());


            System.out.println("\n--- 尝试取消一个未支付订单 ---");
            CreateOrderCommand createCommand2 = new CreateOrderCommand("customer456", 50.00);
            Order order2 = orderApplicationService.createOrder(createCommand2);
            System.out.println("订单2当前状态: " + order2.getStatus());
            CancelOrderCommand cancelCommand2 = new CancelOrderCommand(order2.getOrderCode(), "客户反悔");
            orderApplicationService.cancelOrder(cancelCommand2);
            Order cancelledOrder2 = orderApplicationService.getOrderByCode(order2.getOrderCode()).get();
            System.out.println("订单2当前状态: " + cancelledOrder2.getStatus());

            System.out.println("\n--- 尝试非法状态转换 ---");
            CreateOrderCommand createCommand3 = new CreateOrderCommand("customer789", 200.00);
            Order order3 = orderApplicationService.createOrder(createCommand3);
            System.out.println("订单3当前状态: " + order3.getStatus());
            try {
                // 直接尝试发货一个草稿状态的订单
                ShipOrderCommand shipCommand3 = new ShipOrderCommand(order3.getOrderCode(), "illegal_tracking");
                orderApplicationService.shipOrder(shipCommand3);
            } catch (IllegalStateException e) {
                System.err.println("错误捕获: " + e.getMessage());
                Order currentOrder3 = orderApplicationService.getOrderByCode(order3.getOrderCode()).get();
                System.out.println("订单3状态未改变: " + currentOrder3.getStatus());
            }
        };
    }
}

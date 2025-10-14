package com.iokays.sample.config;

import com.iokays.sample.core.domain.OrderStateMachineEvents;
import com.iokays.sample.core.domain.OrderStates;
import com.iokays.sample.core.domain.Order;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<OrderStates, OrderStateMachineEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderStateMachineEvents> states) throws Exception {
        states
                .withStates()
                .initial(OrderStates.DRAFT) // 初始状态
                .states(EnumSet.allOf(OrderStates.class)) // 定义所有可能的状态
                .end(OrderStates.COMPLETED) // 成功终止状态
                .end(OrderStates.CANCELED); // 失败终止状态
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStates, OrderStateMachineEvents> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderStates.DRAFT).target(OrderStates.PENDING_PAYMENT).event(OrderStateMachineEvents.CREATE_ORDER)
                .action(createOrderAction()) // 触发 CREATE_ORDER 事件后执行的动作
                .and().withExternal()
                .source(OrderStates.PENDING_PAYMENT).target(OrderStates.PAID).event(OrderStateMachineEvents.PAY_ORDER)
                .action(payOrderAction())
                .and().withExternal()
                .source(OrderStates.PAID).target(OrderStates.SHIPPED).event(OrderStateMachineEvents.SHIP_ORDER)
                .action(shipOrderAction())
                .and().withExternal()
                .source(OrderStates.SHIPPED).target(OrderStates.DELIVERED).event(OrderStateMachineEvents.DELIVER_ORDER)
                .action(deliverOrderAction())
                .and().withExternal()
                .source(OrderStates.DELIVERED).target(OrderStates.COMPLETED).event(OrderStateMachineEvents.COMPLETE_ORDER)
                .action(completeOrderAction())

                // 取消订单的转换
                .and().withExternal()
                .source(OrderStates.DRAFT).target(OrderStates.CANCELED).event(OrderStateMachineEvents.CANCEL_ORDER)
                .action(cancelOrderAction())
                .and().withExternal()
                .source(OrderStates.PENDING_PAYMENT).target(OrderStates.CANCELED).event(OrderStateMachineEvents.CANCEL_ORDER)
                .action(cancelOrderAction())
                .and().withExternal()
                .source(OrderStates.PAID).target(OrderStates.CANCELED).event(OrderStateMachineEvents.CANCEL_ORDER)
                .action(cancelOrderAction(), cancelOrderErrorAction()); // 示例：取消失败的恢复操作
    }

    // --- Actions ---

    /**
     * 现有设计非常适合处理聚合根内部的逻辑，但当一个状态转换需要与外部世界（即其他领域服务或限界上下文）交互时，我们就需要一个清晰的模式来处理，同时不能破坏领域驱动设计（DDD）的原则。
     *
     * 方案一：将 Action 实现为独立的 Spring Bean (将方法，提炼成类) implements Action<OrderStates, OrderStateMachineEvents>
     *
     */

    /**
     * 创建订单的 Action
     */
    private Action<OrderStates, OrderStateMachineEvents> createOrderAction() {
        return context -> {
            Order order = context.getExtendedState().get("aggregate", Order.class);
            // 这里可以执行一些创建订单后的初始化逻辑，例如记录日志等
            System.out.println("Action: Order created. Order Code: " + order.getOrderCode());
        };
    }

    /**
     * 支付订单的 Action
     */
    private Action<OrderStates, OrderStateMachineEvents> payOrderAction() {
        return context -> {
            Order order = context.getExtendedState().get("aggregate", Order.class);
            // 订单支付成功后执行的业务逻辑
            order.pay();
            System.out.println("Action: Order " + order.getOrderCode() + " state changed to PAID.");
        };
    }

    /**
     * 发货的 Action
     */
    private Action<OrderStates, OrderStateMachineEvents> shipOrderAction() {
        return context -> {
            Order order = context.getExtendedState().get("aggregate", Order.class);
            order.ship();
            System.out.println("Action: Order " + order.getOrderCode() + " state changed to SHIPPED.");
        };
    }

    /**
     * 签收的 Action
     */
    private Action<OrderStates, OrderStateMachineEvents> deliverOrderAction() {
        return context -> {
            Order order = context.getExtendedState().get("aggregate", Order.class);
            order.deliver();
            System.out.println("Action: Order " + order.getOrderCode() + " state changed to DELIVERED.");
        };
    }

    /**
     * 完成订单的 Action
     */
    private Action<OrderStates, OrderStateMachineEvents> completeOrderAction() {
        return context -> {
            Order order = context.getExtendedState().get("aggregate", Order.class);
            order.complete();
            System.out.println("Action: Order " + order.getOrderCode() + " state changed to COMPLETED.");
        };
    }

    /**
     * 取消订单的 Action
     */
    private Action<OrderStates, OrderStateMachineEvents> cancelOrderAction() {
        return context -> {
            Order order = context.getExtendedState().get("aggregate", Order.class);
            order.cancel();
            System.out.println("Action: Order " + order.getOrderCode() + " cancelled. State changed to CANCELED.");
        };
    }

    /**
     * 取消订单失败的 Error Action
     */
    private Action<OrderStates, OrderStateMachineEvents> cancelOrderErrorAction() {
        return context -> {
            System.err.println("Error Action: Failed to cancel order. Original state was: " + context.getSource().getId());
            // 可以在这里进行错误处理，例如回滚操作、记录错误日志等
        };
    }

    // --- Guards (可选) ---
    // 示例：只有订单金额大于0才能支付
    private Guard<OrderStates, OrderStateMachineEvents> amountGreaterThanZeroGuard() {
        return context -> {
            Order order = context.getExtendedState().get("aggregate", Order.class);
            return order != null && order.getAmount() > 0;
        };
    }
}
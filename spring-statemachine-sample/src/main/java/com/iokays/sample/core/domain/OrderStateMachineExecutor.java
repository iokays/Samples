package com.iokays.sample.core.domain;

import lombok.AllArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import java.util.Map;

// 泛型 S 和 E 依然保持通用，但 execute 方法特化了 Order
@Component
@AllArgsConstructor
public class OrderStateMachineExecutor<S, E> {

    private final StateMachineFactory<S, E> stateMachineFactory;

    /**
     * 执行状态转换的核心方法
     *
     * @param aggregate 目标聚合根实例 (此处已明确为 Order 类型)
     * @param event     要触发的事件
     * @param params    传递给 action 的参数，通常是一个 Command 对象或 Map
     * @return 如果状态转换成功，则返回 true；否则返回 false
     */
    public boolean execute(Order aggregate, E event, Map<String, Object> params) {
        // 1. 获取一个干净的状态机实例
        StateMachine<S, E> stateMachine = stateMachineFactory.getStateMachine();

        // 2. 将聚合根的当前状态“喂”给状态机
        stateMachine.stop();
        stateMachine.getStateMachineAccessor().doWithAllRegions(sma -> {
            // 这里从 Order 聚合根获取状态，假设 S 是 States 类型
            sma.resetStateMachine(new DefaultStateMachineContext<>((S) aggregate.getStatus(), null, null, null));
        });

        // 修正点：将聚合根实例和参数放入 StateMachine 实例的 ExtendedState 中
        stateMachine.getExtendedState().getVariables().put("aggregate", aggregate); // 使用通用名称
        if (params != null) {
            stateMachine.getExtendedState().getVariables().putAll(params);
        }

        stateMachine.start();

        // 3. 发送事件给状态机，触发流程
        boolean success = stateMachine.sendEvent(event);

        // 4. 如果成功，将新状态写回聚合根
        if (success) {
            // 这里直接对 Order 实例调用 transitionTo 方法
            // 假设 S 也是 States 类型，因为 Order.getStatus() 返回 States
            aggregate.transitionTo((OrderStates) stateMachine.getState().getId());
        }

        return success;
    }
}

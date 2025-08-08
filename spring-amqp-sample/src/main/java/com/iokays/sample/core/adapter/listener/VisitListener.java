package com.iokays.sample.core.adapter.listener;

import com.iokays.sample.core.service.CounterApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@AllArgsConstructor
public class VisitListener {

    private final CounterApplicationService counterApplicationService;

    /**
     * direct模式，监听指定队列
     * 直接监听队列，省略默认交换机声明 默认交换机类型: direct. 路由等于队列名称
     */
    @RabbitListener(queues = "visit")
    public void visit(VisitedCopy message) {
        log.info("接收到MQ消息: {}", message);
        counterApplicationService.increment(message.customerId, message.time);
    }

    /**
     * 使用Fanout广播模式，监听所有队列, 因为是用的是默认的交换机(amq.****)，所以要显示声明交换机类型
     * 第一个队列
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue("fanout_visit"), exchange = @Exchange(value = "amq.fanout", type = ExchangeTypes.FANOUT)))
    public void fanoutVisit(VisitedCopy message) {
        log.info("接收到MQ广播消息: {}", message);
        counterApplicationService.increment(message.customerId, message.time);
    }

    /**
     * 使用Fanout广播模式，监听所有队列, 因为是用的是默认的交换机(amq.****)，所以要显示声明交换机类型
     * 第二个队列
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue("fanout_visit_2"), exchange = @Exchange(value = "amq.fanout", type = ExchangeTypes.FANOUT)))
    public void fanoutVisit2(VisitedCopy message) {
        log.info("接收到MQ广播消息2: {}", message);
        counterApplicationService.increment(message.customerId, message.time);
    }

    /**
     * 使用Topic模式，监听模糊匹配(通配符)队列, 因为是用的是默认的交换机(amq.****)，所以要显示声明交换机类型
     * 第一个队列
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue("topic_visit"), exchange = @Exchange(value = "amq.topic", type = ExchangeTypes.TOPIC), key = "visit.*"))
    public void topicVisit(VisitedCopy message) {
        log.info("接收到MQ模糊匹配消息: {}", message);
        counterApplicationService.increment(message.customerId, message.time);
    }

    /**
     * 使用Topic模式，监听模糊匹配(通配符)队列, 因为是用的是默认的交换机(amq.****)，所以要显示声明交换机类型
     * 第二个队列
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue("topic_visit_2"), exchange = @Exchange(value = "amq.topic", type = ExchangeTypes.TOPIC), key = "*.customer"))
    public void topicVisit2(VisitedCopy message) {
        log.info("接收到MQ模糊匹配消息: {}", message);
        counterApplicationService.increment(message.customerId, message.time);
    }

    //Headers

    /**
     * 使用Headers模式，根据消息头（Headers）匹配
     * 第一个队列
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("headers_visit"),
            exchange = @Exchange(
                    value = "amq.headers",
                    type = ExchangeTypes.HEADERS,
                    ignoreDeclarationExceptions = "true"  // 避免重复声明内置交换机
            ),
            arguments = {
                    @Argument(name = "event_class", value = "customer"),  // 匹配 header 中, 是不支持模糊匹配的
                    @Argument(name = "event_type", value = "visit"),  // 匹配 header 中, 是不支持模糊匹配的
                    @Argument(name = "x-match", value = "any")  // 只要匹配任意一个.  完全匹配所有条件使用 all
            }
    ))
    public void headersVisit(VisitedCopy message) {
        log.info("接收到MQ消息头匹配消息: {}", message);
        counterApplicationService.increment(message.customerId, message.time);
    }

    // 新建一个Visited的副本类, 用于模拟跨模块,平台的可序列化消息的传输
    public record VisitedCopy(String customerId, LocalDateTime time) {
    }
}

package com.iokays.sample.core.service;

import com.iokays.sample.core.domain.event.Visited;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class VisitApplicationService {

    //在实际的项目中, 提供一个通用的发送服务接口, 尽量避免将技术栈直接暴露给业务层.
    private final RabbitTemplate rabbitTemplate;

    public void visit(String customerId) {
        final LocalDateTime now = LocalDateTime.now();
        log.info("customerId: {}, visitTime: {}", customerId, now);

        //一个exchange为空的消息, 会发送到routingKey指定的队列中, 交换机类型: direct, Routing Key 完全匹配, 一对一
        rabbitTemplate.convertAndSend("visit", new Visited(customerId, LocalDateTime.now())); //exchange: 默认空, routingKey: 为队列名

        // 交换机类型: Fanout, 忽略 Routing Key，广播到所有队列.
        rabbitTemplate.convertAndSend("amq.fanout", null, new Visited(customerId, LocalDateTime.now()));

        // 交换机类型: Topic, Routing Key 匹配规则, 一对多
        rabbitTemplate.convertAndSend("amq.topic", "visit.customer", new Visited(customerId, LocalDateTime.now()));

        // 交换机类型: Headers, Headers 匹配规则, 一对多
        rabbitTemplate.convertAndSend("amq.headers", null, new Visited(customerId, LocalDateTime.now()), message -> {
            message.getMessageProperties().getHeaders().put("event_class", "customer");
            return message;
        });
        // 交换机类型: Headers, Headers 匹配规则, 一对多
        rabbitTemplate.convertAndSend("amq.headers", null, new Visited(customerId, LocalDateTime.now()), message -> {
            message.getMessageProperties().getHeaders().put("event_type", "visit");
            return message;
        });
    }

}

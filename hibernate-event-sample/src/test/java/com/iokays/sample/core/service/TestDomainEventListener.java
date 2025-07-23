package com.iokays.sample.core.service;

import com.iokays.sample.core.common.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
@Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.REPEATABLE_READ)
class TestDomainEventListener {

    protected static List<DomainEvent> events = new CopyOnWriteArrayList<>();

    @EventListener
    public void handle(final DomainEvent<?> evt) {
        //消息发布[入库], 分布式事务本地消息表的持久化的位置
        log.info("消息发布[入库]: 领域事件发送: {}", evt);
        events.add(evt);
    }
}

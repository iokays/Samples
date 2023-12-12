package com.iokays.integration.localmessage.publisher;

import com.iokays.integration.localmessage.event.CreatedOrderEvent;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MyApplicationServiceTestImpl {

    public void create() {
        //TODO 业务操作, 并写入数据库

        //发布事件
        DomainPublisher.publish(new CreatedOrderEvent(UUID.randomUUID().toString(), BigDecimal.TWO, LocalDateTime.now()));
    }

}

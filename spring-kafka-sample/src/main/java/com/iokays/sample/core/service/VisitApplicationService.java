package com.iokays.sample.core.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class VisitApplicationService {

    //在实际的项目中, 提供一个通用的发送服务接口, 尽量避免将技术栈直接暴露给业务层.
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void visit(String customerId) {
        //建议在测试的时候使用 LocalDate等类, 而不是简单的String, Integer类. 来验证序列化工具是否正常工作.
        final LocalDateTime now = LocalDateTime.now();
        log.info("customerId: {}, visitTime: {}", customerId, now);
        kafkaTemplate.send("visit", customerId, now);
    }

}

package com.iokays.sample.config;

import org.springframework.context.annotation.Configuration;

/**
 * 默认的配置类, Spring Boot 已经默认配置 单一 Kafka 的连接信息.
 * 如果有多个 Kafka 集群, 需要自行配置. 可以参考:
 * {@link org.springframework.boot.kafka.autoconfigure.KafkaAutoConfiguration }
 * <p>
 * 如果需要自定义配置序列化, 可以参考 application.yml 显式配置的生产者,消费者的序列化配置.
 * 因为只要引入了 jackson-datatype-jsr310, 会默认配置 jackson 的序列化. 详见: JacksonUtils.enhancedObjectMapper()
 */
@Configuration
public class KafkaConfiguration {


}


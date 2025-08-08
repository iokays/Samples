package com.iokays.sample.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认的配置类, Spring Boot 已经默认配置 单一 RabbitMQ 的连接信息.
 * 如果有多个 RabbitMQ 集群, 需要自行配置. 可以参考:
 * {@link org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration }
 * <p>
 * 现在我们只需要关注 序列化部分.
 */
@Configuration
public class AMQPConfiguration {

    /**
     * 创建 ObjectMapper, 用于序列化消息.
     * 推荐自定义一个专门用于MQ的序列化类, 避免与使用全局的 ObjectMapper 混用.
     *
     * @return {@link ObjectMapper}
     */
    private ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    /**
     * 代替默认的 SimpleMessageConverter, 使用 Jackson2JsonMessageConverter, 可以跨平台序列化消息
     *
     * @return MessageConverter
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter(this.objectMapper());
    }

}


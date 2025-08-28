package com.iokays.sample.core.adapter.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

import java.time.Duration;

@Configuration
public class RedisStreamListener {

    private static final String STREAM_KEY = "my-stream";
    private static final String GROUP = "my-group";
    private static final String CONSUMER = "consumer-1";

    @Bean(destroyMethod = "stop")
    public StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamContainer(
            RedisConnectionFactory connectionFactory,
            StreamListener<String, MapRecord<String, String, String>> streamListener) {

        // 配置容器选项
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> containerOptions =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                        .builder()
                        .pollTimeout(Duration.ofMillis(100))
                        .errorHandler(Throwable::printStackTrace)
                        .build();

        // 创建容器
        StreamMessageListenerContainer<String, MapRecord<String, String, String>> container =
                StreamMessageListenerContainer.create(connectionFactory, containerOptions);

        // 确保消费组存在（如果不存在则创建）
        try {
            connectionFactory.getConnection().streamCommands().xGroupCreate(
                    STREAM_KEY.getBytes(),
                    GROUP,
                    ReadOffset.from("0-0"),  // 从头开始，生产环境可以用 ReadOffset.latest()
                    true                     // 已存在就忽略
            );
        } catch (Exception e) {
            System.out.println("消费组已存在: " + GROUP);
        }

        // 使用消费组订阅（自动 ack）
        container.receiveAutoAck(
                Consumer.from(GROUP, CONSUMER),
                StreamOffset.create(STREAM_KEY, ReadOffset.lastConsumed()),
                streamListener
        );

        container.start();
        return container;
    }
}

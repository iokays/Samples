package com.iokays.sample.core.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@AllArgsConstructor
public class RedisStreamApplicationService implements StreamListener<String, MapRecord<String, String, String>> {

    private final RedisTemplate<String, String> redisTemplate;
    //有消费
    private final AtomicBoolean hasConsumed = new AtomicBoolean(false);

    public void publish(Map<String, String> map) {
        final var record = StreamRecords.string(map).withStreamKey("my-stream");
        var recordId = redisTemplate.opsForStream().add(record);
        System.out.println("RecordId: " + recordId);
    }

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        hasConsumed.set(true);
        log.info("MessageId: {}", message.getId());
        log.info("Stream: {}", message.getStream());
        log.info("Body: {}", message.getValue());

        // 添加更详细的日志
        if (log.isDebugEnabled()) {
            log.debug("Message details - ID: {}, Stream: {}, Value: {}",
                    message.getId(), message.getStream(), message.getValue());
        }
    }

    public Boolean hasConsumed() {
        return hasConsumed.get();
    }
}

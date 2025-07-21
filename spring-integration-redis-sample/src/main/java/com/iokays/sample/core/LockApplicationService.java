package com.iokays.sample.core;

import com.iokays.sample.config.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class LockApplicationService {

    @DistributedLock(value = "lock:sample", key = "#message.id", unit = ChronoUnit.SECONDS)
    public void send(Message message, Runnable runnable) {
        log.info("Sending message: {}", message);
        runnable.run();
    }

    public record Message(Integer id, String text) {}

}

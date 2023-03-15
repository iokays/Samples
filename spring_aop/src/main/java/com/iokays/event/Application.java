package com.iokays.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan(basePackages = "com.iokays.event")
@EnableAsync
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext content = new AnnotationConfigApplicationContext(Application.class);
        final MyApplicationEventPublisher publisher = content.getBean(MyApplicationEventPublisher.class);
        publisher.publicEvent();
    }

    @Bean
    public TaskExecutor asyncSimpleExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    public TaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        return executor;
    }



}

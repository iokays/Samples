package com.iokays.sample.config;

import lombok.AllArgsConstructor;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 作用: Job使用Spring的依赖注入, 这样可以Job中注入Spring的Bean.
 */
@Component
@AllArgsConstructor
public class MySpringBeanJobFactory extends org.springframework.scheduling.quartz.SpringBeanJobFactory {

    private final ApplicationContext applicationContext;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 通过 Spring 容器创建 Job 实例
        Object job = super.createJobInstance(bundle);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(job);
        return job;
    }

}

package com.iokays.sample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Quartz的配置, 因为该用例中使用了SpringBoot的自动配置, 所以只需要在配置文件中配置好数据库连接信息即可
 *
 * @QuartzDataSource
 * @QuartzTransactionManager 如果有多个数据源, 需要使用
 * @QuartzDataSource注解来指定使用哪个数据源,
 * @QuartzTransactionManager 指定哪个事务管理
 */
@Slf4j
@Configuration
public class QuartzConfiguration {

    /**
     * 使用Spring的依赖注入, 这样可以Job中注入Spring的Bean.
     *
     * @param jobFactory
     * @return
     */
    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactory(final MySpringBeanJobFactory jobFactory) {
        return schedulerFactoryBean -> schedulerFactoryBean.setJobFactory(jobFactory);
    }

}

package com.iokays.sample.config;

import jakarta.persistence.EntityManagerFactory;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 覆盖了 flowable的自动配置 详见如下:
 * </p>
 * org.flowable.spring.boot.ProcessEngineAutoConfiguration
 * org.flowable.spring.boot.ProcessEngineServicesAutoConfiguration
 * org.flowable.spring.boot.FlowableJpaAutoConfiguration
 * </p>
 * 也可以去掉改自定义配置, 使用默认的配置
 *
 */
@Configuration
public class FlowableConfiguration {

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(
            DataSource dataSource,
            PlatformTransactionManager transactionManager,
            EntityManagerFactory entityManagerFactory,
            ResourcePatternResolver resourcePatternResolver
    ) throws IOException {
        final var processEngineConfiguration = new SpringProcessEngineConfiguration();
        //flowable工作流引擎的JDBC配置
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setTransactionManager(transactionManager);

        //flowable工作流数据的JPA配置
        processEngineConfiguration.setJpaEntityManagerFactory(entityManagerFactory);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");
        processEngineConfiguration.setJpaCloseEntityManager(true);
        processEngineConfiguration.setJpaHandleTransaction(true);
        processEngineConfiguration.setAsyncExecutorActivate(false);

        // 自动部署 /resources/processes/ 下的流程文件
        Resource[] resources = resourcePatternResolver.getResources("classpath*:/processes/**/*.bpmn20.xml");
        processEngineConfiguration.setDeploymentResources(resources);

        return processEngineConfiguration;
    }

}


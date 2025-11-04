// Path: Samples/jackson-jpa-lazy-loading-sample/src/main/java/com/iokays/sample/jackson/jpa/solution/hibernate_module/HibernateModuleConfiguration.java
package com.iokays.sample.jackson.jpa.solution.hibernate_module;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 提供Hibernate6Module的Bean，以便可以注入到Controller中。
 * 这个配置会修改全局ObjectMapper。
 */
@Configuration
public class HibernateModuleConfiguration {

    @Bean
    public Hibernate6Module hibernate6Module() {
        return new Hibernate6Module();
    }
}

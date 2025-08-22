package com.iokays.sample.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class BeanValueConfiguration {

    @Value("#{beanObject.name}")
    private String name;

}

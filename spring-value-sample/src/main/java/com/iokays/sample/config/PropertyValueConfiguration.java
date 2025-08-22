package com.iokays.sample.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Configuration
public class PropertyValueConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.properties.array}")
    private Integer[] array;


    @Value("#{${spring.properties.map}}")
    private Map<String, String> map;

}

package com.iokays.sample.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class SpELValueConfiguration {

    @Value("#{ T(java.lang.Integer).valueOf(100) * 99 }")
    public Integer intValue;
    @Value("#{ 100 * 99 }")
    public Integer anotherIntValue;
    @Value("#{systemProperties['user.name']}")
    private String userName;

}

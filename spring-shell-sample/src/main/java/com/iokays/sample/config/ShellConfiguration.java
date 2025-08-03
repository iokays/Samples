package com.iokays.sample.config;

import lombok.extern.slf4j.Slf4j;
import org.jline.utils.AttributedString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

@Slf4j
@Configuration
public class ShellConfiguration {
    @Bean
    public PromptProvider promptProvider() {
        return () -> new AttributedString("my-sheller:>");
    }

}

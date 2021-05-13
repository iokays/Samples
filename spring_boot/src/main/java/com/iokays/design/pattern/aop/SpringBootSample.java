package com.iokays.design.pattern.aop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Objects;

/**
 * @author pengyuanbing
 */
@SpringBootApplication
public class SpringBootSample {

    public static void main(String[] args) throws IOException {
        final ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootSample.class, args);
        Objects.requireNonNull(ctx);
        System.in.read();
        ctx.close();
    }
}

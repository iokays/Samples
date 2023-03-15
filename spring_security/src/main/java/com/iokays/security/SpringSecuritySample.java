package com.iokays.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@SpringBootApplication
public class SpringSecuritySample {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecuritySample.class, args);
    }

}

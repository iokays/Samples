package com.iokays.sample.config;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class ResourceValueConfiguration {

    @Value("classpath:text-sample.txt")
    private Resource resource;

    @Value("classpath:text0-sample.txt,classpath:text1-sample.txt")
    private Resource[] resources;

    public List<String> content() {
        try {
            return IOUtils.readLines(resource.getInputStream(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> content0() {
        try {
            return IOUtils.readLines(resources[0].getInputStream(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> content1() {
        try {
            return IOUtils.readLines(resources[1].getInputStream(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

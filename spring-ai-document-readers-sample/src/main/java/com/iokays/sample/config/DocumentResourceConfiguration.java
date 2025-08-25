package com.iokays.sample.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Getter
@Configuration
public class DocumentResourceConfiguration {

    @Value("classpath:json-sample.json")
    private Resource json;

    @Value("classpath:text-sample.txt")
    private Resource text;

    @Value("classpath:html-sample.html")
    private Resource html;

    @Value("classpath:markdown-sample.md")
    private Resource markdown;

    @Value("classpath:pdf-sample.pdf")
    private Resource pdf;

    @Value("classpath:excel-sample.xlsx")
    private Resource excel;


}

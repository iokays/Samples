package com.iokays.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@SpringBootApplication
@CommandScan //启动@Command的扫描, 如果缺失, 含有@Command注解的类不会生效, 也可以使用 @EnableCommand 指定要启动的@Command 类
// @EnableCommand(CustomerCommander.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

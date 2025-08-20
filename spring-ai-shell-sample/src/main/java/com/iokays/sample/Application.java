package com.iokays.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@SpringBootApplication
@CommandScan
public class Application {

    static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

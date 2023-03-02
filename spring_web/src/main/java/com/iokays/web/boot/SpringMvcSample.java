package com.iokays.web.boot;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@SpringBootApplication
@EnableSwagger2
public class SpringMvcSample {

    @ApiOperation(value = "/", notes = "print hello")
    @GetMapping(value = "/")
    public String hello(@RequestParam(value = "value", required = false) String value,
                        HttpServletRequest request, HttpServletResponse response) {
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcSample.class, args);
    }

}

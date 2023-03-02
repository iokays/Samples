package com.iokays.work.domain;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class JasyptServiceTest {

    @Resource
    private JasyptService jasyptService;

    @Test
    public void testRun() {
    }

}
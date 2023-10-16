package com.iokays.boot.integration;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Tester {

    @Resource
    private UserRegisterMessageGateway userRegisterMessageGateway;

    @Test
    void test() {
        userRegisterMessageGateway.registerMessageFlow(new User("apple"));
    }

}

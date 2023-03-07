package com.iokays.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iokays.web.domain.User;
import jakarta.annotation.Resource;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Resource
    private MockMvc mm;

    @Resource
    private ObjectMapper objectMapper;

    private static final EasyRandom er = new EasyRandom();

    @Test
    public void testUsers() throws Exception {
        MvcResult mvcResult =  mm.perform(
                MockMvcRequestBuilders.get("/users")).
                andExpect( MockMvcResultMatchers.status().isOk()).andReturn();
        final var response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
    }

    @Test
    public void testUser() throws Exception {
        MvcResult mvcResult =  mm.perform(
                        MockMvcRequestBuilders.post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        er.nextObject(User.class)
                                ))
                ).andExpect( MockMvcResultMatchers.status().isOk())
                .andReturn();
        final var response = mvcResult.getResponse();
        logger.info("业务读取RequestBody数据：{}", response.getContentAsString());
    }




}
package com.iokays.sample.core.adapter.web;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void test401() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().is(401));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void test403() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().is(403));
    }


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void test200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().is(200));
    }


}
// Path: Samples/jackson-jpa-lazy-loading-sample/src/test/java/com/iokays/sample/jackson/jpa/SolutionControllerTest.java
package com.iokays.sample.jackson.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iokays.sample.jackson.jpa.solution.jsonignore.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SolutionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long departmentId;

    @BeforeEach
    void setUp() throws Exception {
        // 为JsonIgnore测试准备数据
        Department deptJsonIgnore = new Department();
        deptJsonIgnore.setName("物理系");

        mockMvc.perform(post("/solution/json-ignore/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deptJsonIgnore)))
                .andExpect(status().isOk());

        // 为Hibernate Module和DTO测试准备数据
        com.iokays.sample.jackson.jpa.model.Department dept = new com.iokays.sample.jackson.jpa.model.Department();
        dept.setName("化学系");

        String response = mockMvc.perform(post("/solution/hibernate-module/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dept)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        departmentId = objectMapper.readTree(response).get("id").asLong();
    }

    @Test
    void whenUsingJsonIgnore_thenFieldIsExcluded() throws Exception {
        mockMvc.perform(get("/solution/json-ignore/departments/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("物理系"))
                .andExpect(jsonPath("$.courses").doesNotExist()); // 验证courses字段不存在
    }

    @Test
    void whenUsingHibernateModule_thenFieldIsNull() throws Exception {
        mockMvc.perform(get("/solution/hibernate-module/departments/{id}", departmentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(departmentId))
                .andExpect(jsonPath("$.name").value("化学系"))
                .andExpect(jsonPath("$.courses").isEmpty()); // 验证courses字段为null
    }

    @Test
    void whenUsingDto_thenOnlyDtoFieldsArePresent() throws Exception {
        mockMvc.perform(get("/solution/dto/departments/{id}", departmentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(departmentId))
                .andExpect(jsonPath("$.name").value("化学系"))
                .andExpect(jsonPath("$.courses").doesNotExist()); // 验证courses字段不存在
    }
}

// Path: Samples/jackson-jpa-lazy-loading-sample/src/test/java/com/iokays/sample/jackson/jpa/ProblemControllerTest.java
package com.iokays.sample.jackson.jpa;

import com.iokays.sample.jackson.jpa.model.Course;
import com.iokays.sample.jackson.jpa.model.Department;
import com.iokays.sample.jackson.jpa.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProblemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Long departmentId;

    @BeforeEach
    void setUp() {
        Department department = new Department();
        department.setName("计算机科学");

        Course course = new Course();
        course.setName("数据结构");

        department.setCourses(List.of(course));
        Department saved = departmentRepository.save(department);
        departmentId = saved.getId();
    }

    /**
     * 需要去掉 HibernateModuleConfiguration 的配置。
     * @throws Exception
     */
    @Test
    void whenGetDepartment_thenThrowsLazyInitializationException() throws Exception {
        //实际是500异常
        mockMvc.perform(get("/problem/departments/{id}", departmentId))
                .andExpect(status().isInternalServerError());
    }
}

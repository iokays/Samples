// Path: Samples/jackson-jpa-lazy-loading-sample/src/main/java/com/iokays/sample/jackson/jpa/solution/hibernate_module/HibernateModuleController.java
package com.iokays.sample.jackson.jpa.solution.hibernate_module;

import com.iokays.sample.jackson.jpa.model.Department;
import com.iokays.sample.jackson.jpa.repository.DepartmentRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 这个Controller使用一个局部配置的ObjectMapper，注册了HibernateModule。
 */
@RestController
@RequestMapping("/solution/hibernate-module/departments")
public class HibernateModuleController {

    private final DepartmentRepository repository;

    public HibernateModuleController(DepartmentRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public Department post(@RequestBody Department department) {
        // 注意：这里我们复用的是 com.iokays.sample.jackson.jpa.model.Department
        return repository.save(department);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public Optional<Department> get(@PathVariable("id") Long id) {
        return repository.findById(id);
    }
}

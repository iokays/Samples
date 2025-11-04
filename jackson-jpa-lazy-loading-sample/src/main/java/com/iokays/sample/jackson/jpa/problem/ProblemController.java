// Path: Samples/jackson-jpa-lazy-loading-sample/src/main/java/com/iokays/sample/jackson/jpa/problem/ProblemController.java
package com.iokays.sample.jackson.jpa.problem;

import com.iokays.sample.jackson.jpa.model.Department;
import com.iokays.sample.jackson.jpa.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 用于复现问题的Controller。
 * 当获取Department时，Jackson会尝试序列化courses字段，
 * 但此时JPA session已经关闭，从而触发LazyInitializationException。
 */
@RestController
@RequestMapping("/problem/departments")
@AllArgsConstructor
public class ProblemController {

    private final DepartmentRepository repository;

    @GetMapping("/{id}")
    public Optional<Department> get(@PathVariable("id") Long id) {
        return repository.findById(id);
    }
}

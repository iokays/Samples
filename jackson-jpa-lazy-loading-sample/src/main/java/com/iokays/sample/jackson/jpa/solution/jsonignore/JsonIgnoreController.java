// Path: Samples/jackson-jpa-lazy-loading-sample/src/main/java/com/iokays/sample/jackson/jpa/solution/jsonignore/JsonIgnoreController.java
package com.iokays.sample.jackson.jpa.solution.jsonignore;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/solution/json-ignore/departments")
@AllArgsConstructor
public class JsonIgnoreController {

    private final DepartmentJsonIgnoreRepository repository;

    @PostMapping
    @Transactional
    public Department post(@RequestBody Department department) {
        return repository.save(department);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public Optional<Department> get(@PathVariable("id") Long id) {
        return repository.findById(id);
    }
}

@Repository
interface DepartmentJsonIgnoreRepository extends JpaRepository<Department, Long> {}

// Path: Samples/jackson-jpa-lazy-loading-sample/src/main/java/com/iokays/sample/jackson/jpa/solution/dto/DtoController.java
package com.iokays.sample.jackson.jpa.solution.dto;

import com.iokays.sample.jackson.jpa.model.Department;
import com.iokays.sample.jackson.jpa.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 这个Controller在返回数据前，将持久化层的实体（Entity）
 * 手动映射到API层的传输对象（DTO）。
 * <p>
 * 这是最干净、最健壮的解决方案，因为它完全解耦了API和数据库模型。
 */
@RestController
@RequestMapping("/solution/dto/departments")
@AllArgsConstructor
public class DtoController {

    private final DepartmentRepository repository;

    @PostMapping
    @Transactional
    public DepartmentDto post(@RequestBody Department department) {
        Department saved = repository.save(department);
        return new DepartmentDto(saved.getId(), saved.getName());
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public Optional<DepartmentDto> get(@PathVariable("id") Long id) {
        return repository.findById(id)
                .map(d -> new DepartmentDto(d.getId(), d.getName()));
    }
}

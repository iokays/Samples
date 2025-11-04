// Path: Samples/jackson-jpa-lazy-loading-sample/src/main/java/com/iokays/sample/jackson/jpa/repository/DepartmentRepository.java
package com.iokays.sample.jackson.jpa.repository;

import com.iokays.sample.jackson.jpa.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

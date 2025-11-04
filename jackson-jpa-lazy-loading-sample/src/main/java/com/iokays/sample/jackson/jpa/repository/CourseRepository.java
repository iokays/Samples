// Path: Samples/jackson-jpa-lazy-loading-sample/src/main/java/com/iokays/sample/jackson/jpa/repository/CourseRepository.java
package com.iokays.sample.jackson.jpa.repository;

import com.iokays.sample.jackson.jpa.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

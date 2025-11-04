// Path: Samples/jackson-jpa-lazy-loading-sample/src/main/java/com/iokays/sample/jackson/jpa/solution/jsonignore/Course.java
package com.iokays.sample.jackson.jpa.solution.jsonignore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Course_JsonIgnore")
@Table(name = "course_jsonignore")
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}

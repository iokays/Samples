// Path: Samples/jackson-jpa-lazy-loading-sample/src/main/java/com/iokays/sample/jackson/jpa/solution/jsonignore/Department.java
package com.iokays.sample.jackson.jpa.solution.jsonignore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "Department_JsonIgnore") // 使用不同的实体名称
@Table(name = "department_jsonignore")
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * 解决方案1: 使用@JsonIgnore
     * 这个注解告诉Jackson在序列化时完全忽略这个字段。
     */
    @JsonIgnore
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Course> courses;
}

package com.iokays.sample.core.domain.sample;


import com.iokays.sample.core.domain.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@ToString
@Table(name = "my_sub_entity")
public class MySubEntity implements Entity {

    private String name;

}

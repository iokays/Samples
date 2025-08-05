package com.iokays.sample.core.domain.sample;


import com.iokays.sample.core.domain.ValueObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@ToString
@Table(name = "my_embedded_entity")
public class MyEmbeddedEntity extends ValueObject {

    private String address;

}

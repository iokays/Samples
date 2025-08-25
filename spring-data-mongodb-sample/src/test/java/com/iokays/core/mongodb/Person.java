package com.iokays.core.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
public class Person implements Serializable {

    @Id
    String id;

    String name;

    Integer age;

    String firstname;

    @Field("last_name")
    String lastname;

    public Person(final String name, final Integer age) {
        this.name = name;
        this.age = age;
    }

}
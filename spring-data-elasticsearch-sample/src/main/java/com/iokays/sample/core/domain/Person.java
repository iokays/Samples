package com.iokays.sample.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Data
@Document(indexName = "person", createIndex = true)
public class Person {
    @Id
    String id;
    String firstname;
    String lastname;

    @Field(type = FieldType.Date, format = DateFormat.epoch_millis)
    LocalDateTime createDate;
}

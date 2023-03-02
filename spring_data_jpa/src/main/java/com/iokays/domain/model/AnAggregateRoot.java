package com.iokays.domain.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

public class AnAggregateRoot extends AbstractAggregateRoot<AnAggregateRoot> {

    @CreatedDate
    private LocalDateTime createDate;

    @CreatedBy
    private String createBy;


}
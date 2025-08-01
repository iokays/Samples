package com.iokays.sample.core.service;


import com.iokays.sample.core.adapter.persistence.querydsl.CustomerDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class QueryApplicationService {

    private final CustomerDao customerDao;

}


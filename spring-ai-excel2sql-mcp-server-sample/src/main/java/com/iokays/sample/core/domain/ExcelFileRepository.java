package com.iokays.sample.core.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcelFileRepository extends CrudRepository<ExcelFile, Long> {

    ExcelFile findFirstByOrderByFileCodeDesc();

    List<ExcelFile> findByFileCode(Long fileCode);

}

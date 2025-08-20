package com.iokays.sample.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Getter
@ToString
@Table(name = "T_EXCEL_FILE")
public class ExcelFile implements Serializable {
    @Id
    private Long id;

    // 文件Code
    @Setter
    private Long fileCode;

    //表名
    @Setter
    private String tableName;

    //表内容
    @Setter
    private String tableContent;

    //表注释
    @Setter
    private String tableComment;

}

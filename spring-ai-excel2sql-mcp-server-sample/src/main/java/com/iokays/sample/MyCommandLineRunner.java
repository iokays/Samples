package com.iokays.sample;

import com.iokays.sample.core.service.FileApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MyCommandLineRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final FileApplicationService fileApplicationService;
    private final ResourceLoader resourceLoader;

    /**
     * 因为用的是内存数据库, 所以需要手动建表
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        final var sql = """
                CREATE TABLE t_excel_file (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
                    file_code BIGINT NOT NULL COMMENT '文件Code',
                    table_name VARCHAR(255) COMMENT '表名',
                    table_content TEXT COMMENT '表内容（存储JSON或CSV格式数据）',
                    table_comment VARCHAR(500) COMMENT '表注释'
                );
                """;
        jdbcTemplate.execute(sql);

        final var resource = resourceLoader.getResource("classpath:file.xlsx");
        final var fileCode = fileApplicationService.uploadFile("file.xlsx", resource.getContentAsByteArray());
        log.info("fileCode: {}", fileCode);
    }
}

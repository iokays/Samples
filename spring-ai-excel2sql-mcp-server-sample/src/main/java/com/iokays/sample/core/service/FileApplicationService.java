package com.iokays.sample.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.iokays.sample.core.domain.ExcelFile;
import com.iokays.sample.core.domain.ExcelFileRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.poi.ss.usermodel.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@AllArgsConstructor
public class FileApplicationService {

    private static final Long minFileId = 10000L;
    private static final AtomicLong maxFileId = new AtomicLong(minFileId);

    private final ExcelFileRepository excelFileRepository;
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    /**
     * 这里做的简单的处理, 实际应该根据字的类型取值,包括建表语句的字段类型.
     *
     * @param cell
     * @return
     */
    private static String cellValueToString(Cell cell) {
        if (null == cell) {
            return StringUtils.EMPTY;
        }
        return switch (cell.getCellType()) {
            case CellType._NONE, ERROR, BLANK -> StringUtils.EMPTY;
            case NUMERIC, FORMULA -> Double.toString(cell.getNumericCellValue());
            case STRING -> cell.getStringCellValue();
            case BOOLEAN -> cell.getBooleanCellValue() ? "1" : "0";
        };
    }

    private synchronized Long nextFileId() {
        if (maxFileId.get() == minFileId) {
            final var excelFile = excelFileRepository.findFirstByOrderByFileCodeDesc();
            if (null != excelFile) {
                maxFileId.set(excelFile.getFileCode());
            }
        }
        Validate.isTrue(maxFileId.get() >= minFileId, "maxFileId must be greater than minFileId");
        return maxFileId.incrementAndGet();
    }

    /**
     * 上传文件
     * <p>
     * 1. 生成文件ID
     * 2. 解析excel文件
     * 3. 将每个sheet的名称和标题生成 SQL的create table语句 [只支持单表头]
     *
     * @param fileName    文件名
     * @param fileContent 文件内容
     * @return 文件ID
     */
    public Long uploadFile(String fileName, byte[] fileContent) {
        Objects.requireNonNull(fileName, "fileName must not be null");
        Objects.requireNonNull(fileContent, "fileContent must not be null");

        //生成文件ID
        final Long fileId = this.nextFileId();
        try (final Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(fileContent))) {
            log.info("sheet.size:{}", workbook.getNumberOfSheets());
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                final Sheet sheet = workbook.getSheetAt(i);
                final String tableName = "t_excel_%s_%s".formatted(fileId, i);
                final String tableComment = sheet.getSheetName();
                final Row row = sheet.getRow(0);

                final Map<String, String> rowMap = Maps.newLinkedHashMap();
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    rowMap.put("col_" + j, row.getCell(j).getStringCellValue());
                }
                //建表
                this.createTableFromHeaders(tableName, tableComment, rowMap);

                final ExcelFile excelFile = new ExcelFile();
                excelFile.setFileCode(fileId);
                excelFile.setTableName(tableName);
                excelFile.setTableContent(objectMapper.writeValueAsString(rowMap));
                excelFile.setTableComment(tableComment);
                excelFileRepository.save(excelFile);

                //插入数据
                final StringBuilder sql = new StringBuilder("INSERT INTO %s (%s) VALUES \n".formatted(tableName, String.join(",", rowMap.keySet())));

                final List<String> dataList = Lists.newArrayList();
                for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                    final var tempIndex = j;
                    dataList.add("(%s)".formatted(IntStream.range(0, rowMap.size()).mapToObj(k -> "'%s'".formatted(cellValueToString(sheet.getRow(tempIndex).getCell(k)))).collect(Collectors.joining(","))));
                }

                sql.append(String.join(",\n", dataList));

                log.info("tableName: {}, tableComment: {}", tableName, tableComment);

                jdbcTemplate.execute(sql.toString());

            }
        } catch (Exception e) {
            log.error("uploadFile error", e);
            throw new RuntimeException(e);
        }


        return fileId;
    }

    private void createTableFromHeaders(String tableName, String tableComment, Map<String, String> rowMap) {
        if (MapUtils.isEmpty(rowMap)) {
            return;
        }
        // 构建SQL
        StringBuilder sql = new StringBuilder("CREATE TABLE ")
                .append(tableName)
                .append(" (id INT AUTO_INCREMENT PRIMARY KEY");

        rowMap.forEach((k, v) -> sql.append(", ").append(k).append(" TEXT COMMENT '%s'".formatted(v)));

        sql.append(")");
        log.info("create table sql: {}", sql);

        jdbcTemplate.execute(sql.toString());

    }

    public List<ExcelFile> getTables(Long fileCode) {
        return excelFileRepository.findByFileCode(fileCode);
    }

    public List<Map<String, Object>> query(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

}

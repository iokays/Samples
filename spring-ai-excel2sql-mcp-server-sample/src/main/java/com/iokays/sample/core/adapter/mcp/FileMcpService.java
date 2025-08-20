package com.iokays.sample.core.adapter.mcp;

import com.iokays.sample.core.domain.ExcelFile;
import com.iokays.sample.core.service.FileApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class FileMcpService {

    private final FileApplicationService fileApplicationService;

    @Tool(description = "根据文件编码(fileCode), 获取所有的表结构")
    public List<ExcelFile> getTables(Long fileCode) {
        return fileApplicationService.getTables(fileCode);
    }


    @Tool(description = "根据SQL(H2), 获取数据")
    public List<Map<String, Object>> query(String sql) {
        return fileApplicationService.query(sql);
    }

}

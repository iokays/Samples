package com.iokays.sample.core.gen;

import com.querydsl.codegen.BeanSerializer;
import com.querydsl.codegen.JavaTypeMappings;
import com.querydsl.sql.codegen.MetaDataExporter;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.yaml.snakeyaml.Yaml;

import javax.sql.DataSource;
import java.io.File;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * 创建一个与Spring容器不相关的生成类
 * 因为涉及到外部组件的依赖[数据库], 建议在数据库变更后, 手动执行生成操作, 而不是放在每次编译时生成实体.
 * 所以将该实现放在了test 模块.
 */
@Slf4j
@Builder
@AllArgsConstructor
public class QueryDSLExporterRunner implements Serializable {

    private final DataSource dataSource;
    private final String packageName = "com.iokays.sample.core.adapter.persistence.querydsl.model";
    private final String prefixToStrip = "q_";

    public static void main(String[] args) {
        // 1. 读取 application.yml 内容
        final Map<?, ?> soureMap = getYmlMap("application.yml");

        // 2. 配置JDBC 数据源
        final var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(strValue(soureMap, "spring.datasource.driver-class-name"));
        dataSource.setUrl(strValue(soureMap, "spring.datasource.url"));
        dataSource.setUsername(strValue(soureMap, "spring.datasource.username"));
        dataSource.setPassword(strValue(soureMap, "spring.datasource.password"));

        // 3. 生成实体类
        final var exporter = new QueryDSLExporterRunnerBuilder().dataSource(dataSource).build();
        exporter.gen();

    }

    /**
     * 仅适合本地的操作, 未对jar包和容器环境的执行测试.
     */
    public static Map<?, ?> getYmlMap(final String path) {
        return new Yaml().load(QueryDSLExporterRunner.class.getClassLoader().getResourceAsStream(path));
    }

    /**
     * 从嵌套 Map 中获取值（支持 "spring.datasource.url" 格式）
     */
    private static String strValue(Map<?, ?> ymlMap, String path) {
        if (MapUtils.isEmpty(ymlMap) || StringUtils.isBlank(path)) {
            return "";
        }

        String[] keys = path.split("\\.");
        Object current = ymlMap;
        for (String key : keys) {
            if (current instanceof Map<?, ?> map) {
                current = map.get(key);
            } else {
                return ""; // 路径错误
            }
        }
        return Objects.toString(current, "");
    }

    protected void gen() {
        log.info("开始实体的生成");

        Try.run(() -> {
            final var exporter = new MetaDataExporter();
            exporter.setExportViews(false);
            exporter.setTableNamePattern(prefixToStrip + "%");  // 仅匹配以 int_ 开头的表
            exporter.setPackageName(packageName);
            exporter.setTypeMappings(new JavaTypeMappings());
            exporter.setBeanSerializer(new BeanSerializer());

            exporter.setNamingStrategy(new DynamicPrefixNamingStrategy(prefixToStrip)); // 直接传前缀

            exporter.setTargetFolder(new File("src/main/java"));
            exporter.export(dataSource.getConnection().getMetaData());
            log.info("完成实体的生成");
        }).onFailure(e -> log.error("生成实体失败", e));
    }


}

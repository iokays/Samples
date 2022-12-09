package com.iokays.work.domain;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private EnterpriseRepository enterpriseRepository;

    @Resource
    private ElasticsearchOperations operations;

    public int count() {
        final var count = jdbcTemplate.queryForObject("select count(*) from industrial_enterprise_all", Integer.class);
        logger.info("count: {}", count);
        return count;
    }

    public void transfer() {
        IndexOperations indexOps = operations.indexOps(Enterprise.class);
        if (!indexOps.exists()) {
            indexOps.create();
        }
        indexOps.refresh();

        var minEid = "17b3dcf1-52c1-4149-a446-a2bbb656c119";
        final AtomicInteger count = new AtomicInteger();
        while (StringUtils.isNotBlank(minEid)) {

            final List<Enterprise> list = jdbcTemplate.query("select * from industrial_enterprise_all where eid > '" + minEid +"' order by eid limit 10000",
                    BeanPropertyRowMapper.newInstance(Enterprise.class));
            if (CollectionUtils.isEmpty(list)) {
                break;
            }
            logger.info("开始插入数据： 一共{}条", list.size());

            enterpriseRepository.saveAll(list);
            indexOps.refresh();
            minEid = (String) list.get(list.size() - 1).getEid();
            logger.info("minEid: {}, count: {}", minEid, count.addAndGet(list.size()));
        }

    }

}

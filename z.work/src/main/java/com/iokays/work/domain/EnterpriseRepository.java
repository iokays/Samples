package com.iokays.work.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends ElasticsearchRepository<Enterprise, String> {
}

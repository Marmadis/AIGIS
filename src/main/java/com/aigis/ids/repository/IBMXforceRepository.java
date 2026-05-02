package com.aigis.ids.repository;

import com.aigis.ids.entity.IBMXforceInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IBMXforceRepository extends ElasticsearchRepository<IBMXforceInfo,String> {

}

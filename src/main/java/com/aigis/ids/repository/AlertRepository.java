package com.aigis.ids.repository;

import com.aigis.ids.entity.RawAlert;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AlertRepository extends ElasticsearchRepository<RawAlert,String> {

}

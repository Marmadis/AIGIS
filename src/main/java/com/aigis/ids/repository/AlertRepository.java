package com.aigis.ids.repository;

import com.aigis.ids.entity.Alert;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AlertRepository extends ElasticsearchRepository<Alert,String> {

}

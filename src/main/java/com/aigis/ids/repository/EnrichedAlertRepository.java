package com.aigis.ids.repository;

import com.aigis.ids.entity.EnrichedAlert;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EnrichedAlertRepository extends ElasticsearchRepository<EnrichedAlert,String> {

}

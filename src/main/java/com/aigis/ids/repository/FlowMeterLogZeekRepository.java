package com.aigis.ids.repository;

import com.aigis.ids.entity.FlowMeterLogZeek;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface FlowMeterLogZeekRepository extends ElasticsearchRepository<FlowMeterLogZeek,String> {
    Optional<FlowMeterLogZeek> findByUid(String uid);
}

package com.aigis.ids.repository;

import com.aigis.ids.entity.IndicatorOfCompromise;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IocRepository extends ElasticsearchRepository<IndicatorOfCompromise,String> {

    boolean existsByIpAddress(String ipAddress);
}

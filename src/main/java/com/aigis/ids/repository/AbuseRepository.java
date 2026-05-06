package com.aigis.ids.repository;

import com.aigis.ids.entity.AbuseInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface AbuseRepository  extends ElasticsearchRepository<AbuseInfo,String> {

    AbuseInfo findByIpAddress(String ipAddress);
    boolean existsByIpAddress(String ipAddress);
}

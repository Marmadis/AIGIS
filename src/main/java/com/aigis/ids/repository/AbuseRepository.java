package com.aigis.ids.repository;

import com.aigis.ids.entity.AbuseInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;


public interface AbuseRepository  extends ElasticsearchRepository<AbuseInfo,String> {

    Optional<AbuseInfo> findByIpAddress(String ipAddress);
    boolean existsByIpAddress(String ipAddress);
}

package com.aigis.ids.repository;

import com.aigis.ids.entity.VirusTotalInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;


public interface VirusTotalRepository extends ElasticsearchRepository<VirusTotalInfo,String> {

    Optional<VirusTotalInfo> findByIpAddress(String ipAddress);
    boolean existsByIpAddress(String ipAddress);
}



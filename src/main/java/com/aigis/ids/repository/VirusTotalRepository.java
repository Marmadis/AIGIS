package com.aigis.ids.repository;

import com.aigis.ids.entity.VirusTotalInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;



public interface VirusTotalRepository extends ElasticsearchRepository<VirusTotalInfo,String> {

    VirusTotalInfo findByIpAddress(String ipAddress);
    boolean existsByIpAddress(String ipAddress);
}



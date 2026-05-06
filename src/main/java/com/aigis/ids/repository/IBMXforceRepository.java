package com.aigis.ids.repository;

import com.aigis.ids.entity.IBMXforceInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface IBMXforceRepository extends ElasticsearchRepository<IBMXforceInfo,String> {

    IBMXforceInfo findByIpAddress(String ipAddress);
    boolean existsByIpAddress(String ipAddress);
}

package com.aigis.ids.repository;

import com.aigis.ids.entity.RawAlert;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface RawAlertRepository extends ElasticsearchRepository<RawAlert,String> {

    List<RawAlert> findBySourceIp(String sourceIp);
    List<RawAlert> findByDestinationIp(String destinationIp);

}

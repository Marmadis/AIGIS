package com.aigis.ids.repository;

import com.aigis.ids.entity.ConnLogZeek;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface ConnLogZeekRepository extends ElasticsearchRepository<ConnLogZeek,String> {

    Optional<ConnLogZeek> findFirstBySourceIpAndSourcePortAndDestinationIpAndDestinationPortOrDestinationIpAndDestinationPortAndSourceIpAndSourcePort(
            String srcIp1, String srcPort1, String dstIp1, String dstPort1,
            String srcIp2, String srcPort2, String dstIp2, String dstPort2
    );
}

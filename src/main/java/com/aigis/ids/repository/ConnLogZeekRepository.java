package com.aigis.ids.repository;

import com.aigis.ids.entity.ConnLogZeek;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface ConnLogZeekRepository extends ElasticsearchRepository<ConnLogZeek,String> {

    @Query("""
    {
      "bool": {
        "should": [
          {
            "bool": {
              "must": [
                { "term": { "id.orig_h": "?0" } },
                { "term": { "id.orig_p": "?1" } },
                { "term": { "id.resp_h": "?2" } },
                { "term": { "id.resp_p": "?3" } }
              ]
            }
          },
          {
            "bool": {
              "must": [
                { "term": { "id.resp_h": "?0" } },
                { "term": { "id.resp_p": "?1" } },
                { "term": { "id.orig_h": "?2" } },
                { "term": { "id.orig_p": "?3" } }
              ]
            }
          }
        ],
        "minimum_should_match": 1
      }
    }
    """)
    Optional<ConnLogZeek> findConnection(String srcIp, String srcPort, String dstIp, String dstPort);
}

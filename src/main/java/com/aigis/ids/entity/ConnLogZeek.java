package com.aigis.ids.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "zeek-conn",createIndex = false)
public class ConnLogZeek {

    @Id
    private String id;

    @JsonProperty("@timestamp")
    private String timestamp;

    private String uid;

    @JsonProperty("ts")
    private Double ts;

    @JsonProperty("id.orig_h")
    private String sourceIp;

    @JsonProperty("id.orig_p")
    private String sourcePort;

    @JsonProperty("id.resp_h")
    private String destinationIp;

    @JsonProperty("id.resp_p")
    private String destinationPort;

    private String proto;

    @JsonProperty("ip_proto")
    private Integer ipProto;

    @JsonProperty("conn_state")
    private String connState;

    @JsonProperty("orig_pkts")
    private Long origPackets;

    @JsonProperty("resp_pkts")
    private Long respPackets;

    @JsonProperty("orig_ip_bytes")
    private Long origIpBytes;

    @JsonProperty("resp_ip_bytes")
    private Long respIpBytes;

    @JsonProperty("missed_bytes")
    private Long missedBytes;

    private String history;

    @JsonProperty("local_orig")
    private Boolean localOrig;

    @JsonProperty("local_resp")
    private Boolean localResp;
}


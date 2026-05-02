package com.aigis.ids.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document(indexName = "alerts", createIndex = false)
public class Alert {

    @Id
    private String id = UUID.randomUUID().toString();

    private String timestamp;

    @JsonProperty("class")
    private String classificationSnort;

    @JsonProperty("msg")
    private String eventName;

    @JsonProperty("src_addr")
    private String source_ip;

    @JsonProperty("dst_addr")
    private String destination_ip;

    private String priority;

    @JsonProperty("proto")
    private String protocol;
}

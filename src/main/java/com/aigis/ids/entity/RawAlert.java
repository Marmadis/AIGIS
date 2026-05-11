package com.aigis.ids.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document(indexName = "raw_alerts")
public class RawAlert {

    @Id
    private String id = UUID.randomUUID().toString();

    private String timestamp;

    @JsonProperty("class")
    private String classificationSnort;

    @JsonProperty("msg")
    private String eventName;

    @JsonProperty("src_addr")
    private String sourceIp;

    @JsonProperty("dst_addr")
    private String destinationIp;

    private String priority;

    @JsonProperty("proto")
    private String protocol;
}

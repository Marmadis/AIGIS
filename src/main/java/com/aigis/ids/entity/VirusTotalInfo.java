package com.aigis.ids.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document(indexName = "virustotal_info_about_ip_address",createIndex = false)
public class VirusTotalInfo {
    @Id
    private String id = UUID.randomUUID().toString();

    private String ipAddress;

    private String last_analysis_stats;

    private String reputation;

    private String tags;

    private String as_owner;

    private String crowdsourced_context;
}

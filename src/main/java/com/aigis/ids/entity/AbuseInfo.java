package com.aigis.ids.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document(indexName = "AbuseIPDB_info_about_IP_address",createIndex = false)
public class AbuseInfo {
    @Id
    private String id = UUID.randomUUID().toString();

    private String ipAddress;

    private boolean isPublic;

    private int ipVersion;

    private boolean isWhitelisted;

    private int abuseConfidenceScore;

    private String countryCode;

    private String usageType;

    private String isp;

    private String domain;

    private int totalReports;

    private int numDistinctUsers;

    private String lastReportedAt;
}

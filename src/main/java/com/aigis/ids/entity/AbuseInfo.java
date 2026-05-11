package com.aigis.ids.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "abuseipdb_info_about_ip_address")
public class AbuseInfo {

    @Id
    private String id = UUID.randomUUID().toString();

    private String ipAddress;
    private boolean isPublic;
    private int ipVersion;
    private boolean isWhitelisted;
    private int abuseConfidenceScore;
    private String countryCode;
    private String countryName;
    private String usageType;
    private String isp;
    private String domain;
    private List<String> hostnames;
    private boolean isTor;
    private int totalReports;
    private int numDistinctUsers;

    @Field(type = FieldType.Date, format = DateFormat.date_time_no_millis)
    private String lastReportedAt;

    private List<IpReport> reports;

    @Data
    @NoArgsConstructor
    public static class IpReport {
        private String reportedAt;
        private String comment;
        private List<Integer> categories;
        private int reporterId;
        private String fromIpCountryCode;
        private String fromIpCountryName;
    }

    /**
     * Статический метод-обертка для десериализации,
     * так как API AbuseIPDB всегда возвращает объект в поле "data"
     */
    @Data
    public static class AbuseContainer {
        private AbuseInfo data;
    }
}
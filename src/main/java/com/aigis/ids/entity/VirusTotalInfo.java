package com.aigis.ids.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "virustotal_info_about_ip_address")
public class VirusTotalInfo {

    @Id
    private String id = UUID.randomUUID().toString();

    private String ipAddress;

    private int reputation;

    @JsonProperty("as_owner")
    private String asOwner;

    private String network;

    private String country;

    private AnalysisStats lastAnalysisStats;

    private TotalVotes totalVotes;

    @Field(type = FieldType.Date, format = {}, pattern = "epoch_second")
    private Long lastAnalysisDate;

    // Вложенный класс для статистики антивирусов
    @Data
    @NoArgsConstructor
    public static class AnalysisStats {
        private int malicious;
        private int suspicious;
        private int undetected;
        private int harmless;
        private int timeout;
    }

    // Вложенный класс для голосов сообщества
    @Data
    @NoArgsConstructor
    public static class TotalVotes {
        private int harmless;
        private int malicious;
    }

    /**
     * Обертки для корректного парсинга иерархии JSON VirusTotal:
     * Root -> data -> attributes
     */
    @Data
    public static class VTContainer {
        private VTData data;
    }

    @Data
    public static class VTData {
        private String id; // Это IP адрес в формате VT
        private VTAttributes attributes;
    }

    @Data
    public static class VTAttributes extends VirusTotalInfo {
        // Этот класс используется только для маппинга Jackson
    }
}
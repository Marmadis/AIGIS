package com.aigis.ids.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "enriched_alert")
public class EnrichedAlert {

    @Id
    private String id = UUID.randomUUID().toString();

    private String evaluation;

    private String ioc;

    @JsonProperty("threat_type")
    @Field(type = FieldType.Keyword)
    private String threatType;

    @JsonProperty("confidence_score")
    @Field(type = FieldType.Double)
    private double confidenceScore;

    @JsonProperty("processing_time_ms")
    @Field(type = FieldType.Double)
    private double processingTimeMs;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp; // Исправлено

    @Field(type = FieldType.Object)
    private Details details;

    // Если это поле нужно для внутренней логики, оставляем
    private boolean isAttack;

    @Data
    @NoArgsConstructor
    public static class Details {

        @Field(type = FieldType.Object)
        private NetworkNode source;

        @Field(type = FieldType.Object)
        private NetworkNode destination;
    }

    @Data
    @NoArgsConstructor
    public static class NetworkNode {
        private String ip;
        private String verdict;
        private double confidence;
    }
}
package com.aigis.ids.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "enriched_alert")
public class EnrichedAlert {

    @Id
    private String id = UUID.randomUUID().toString();

    @JsonProperty("is_attack")
    private boolean isAttack;

    private String verdict;

    private Details details;

    @JsonProperty("network_context")
    private NetworkContext networkContext;


    private String timestamp;

    @Data
    @NoArgsConstructor
    public static class Details {
        @JsonProperty("confidence_percent")
        private double confidencePercent;

        @JsonProperty("inference_speed_ms")
        private double inferenceSpeedMs;

        @JsonProperty("threat_level")
        private String threatLevel;

        @JsonProperty("full_probability_map")
        private Map<String, Double> fullProbabilityMap;
    }

    @Data
    @NoArgsConstructor
    public static class NetworkContext {
        @JsonProperty("dest_port")
        private int destPort;

        private String protocol;

        @JsonProperty("flow_duration")
        private long flowDuration;
    }
}
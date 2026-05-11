package com.aigis.ids.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record VtAttributes(@JsonProperty("last_analysis_stats") VtAnalysisStats lastAnalysisStats,
                           int reputation,
                           List<String> tags,
                           @JsonProperty("as_owner") String asOwner,
                           @JsonProperty("crowdsourced_context") List<VtContext> crowdsourcedContext,
                           String network,
                           String country,
                           @JsonProperty("last_analysis_date") long lastAnalysisDate) {
}

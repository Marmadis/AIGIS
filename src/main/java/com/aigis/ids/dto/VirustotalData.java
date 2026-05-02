package com.aigis.ids.dto;

public record VirustotalData(
        String ipAddress,
        String last_analysis_stats,
        String reputation,
        String tags,
        String as_owner,
        String crowdsourced_context
) {
}

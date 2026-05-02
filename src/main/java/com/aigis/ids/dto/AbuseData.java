package com.aigis.ids.dto;


public record AbuseData(
        String ipAddress,
        boolean isPublic,
        int ipVersion,
        boolean isWhitelisted,
        int abuseConfidenceScore,
        String countryCode,
        String usageType,
        String isp,
        String domain,
        int totalReports,
        int numDistinctUsers,
        String lastReportedAt
){ }

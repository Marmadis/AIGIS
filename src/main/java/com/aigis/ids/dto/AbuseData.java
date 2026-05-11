package com.aigis.ids.dto;

import java.util.List;

public record AbuseData(String ipAddress,
                        boolean isPublic,
                        int ipVersion,
                        boolean isWhitelisted,
                        int abuseConfidenceScore,
                        String countryCode,
                        String countryName,
                        String usageType,
                        String isp,
                        String domain,
                        List<String> hostnames,
                        int totalReports,
                        int numDistinctUsers,
                        String lastReportedAt,
                        List<AbuseReport> reports) {
}

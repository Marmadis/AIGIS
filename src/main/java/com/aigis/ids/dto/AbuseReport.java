package com.aigis.ids.dto;

import java.util.List;

public record AbuseReport(String reportedAt,
                          String comment,
                          List<Integer> categories,
                          int reporterId,
                          String fromIpCountryCode,
                          String fromIpCountryName) {
}

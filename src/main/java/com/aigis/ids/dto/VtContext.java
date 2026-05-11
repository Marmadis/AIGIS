package com.aigis.ids.dto;

public record VtContext(String title,
                        String details,
                        long timestamp,
                        String severity,
                        String source) {
}

package com.aigis.ids.dto;

public record VtAnalysisStats(int malicious,
                              int suspicious,
                              int undetected,
                              int harmless,
                              int timeout) {
}

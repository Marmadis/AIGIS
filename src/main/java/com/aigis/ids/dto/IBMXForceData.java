package com.aigis.ids.dto;

public record IBMXForceData(
        String ipAddress,
        String cats,
        String subscore,
        String history,
        String linked_entities
) {}

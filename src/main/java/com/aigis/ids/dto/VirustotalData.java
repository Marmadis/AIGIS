package com.aigis.ids.dto;

public record VirustotalData(
        String id,
        String type,
        VtAttributes attributes
) {
}
